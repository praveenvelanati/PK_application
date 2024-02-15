package com.sravan.covidapplication.Activities

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import com.sravan.covidapplication.Adapters.UsersAdapter
import com.sravan.covidapplication.R
import com.sravan.covidapplication.UIModel.UserViewModel
import com.sravan.covidapplication.Utils.Constants
import com.sravan.covidapplication.Utils.NetworkResult
import com.sravan.covidapplication.databinding.ActivityUsersBinding
import com.sravan.covidapplication.models.UserProfile
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UsersActivity : AppCompatActivity() {

    private var _binding: ActivityUsersBinding? = null
    private val binding get() = _binding!!
    private lateinit var userViewModel: UserViewModel
    private lateinit var auth: FirebaseAuth
    private lateinit var userId: String
    private val MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 101
    private lateinit var usersAdapter: UsersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityUsersBinding.inflate(layoutInflater)
        usersAdapter = UsersAdapter(::onItemClicked)
        setContentView(binding.root)

        // start shimmer loading for recyclerView
        binding.shimmerLayout.startShimmer()

        //Initialize view model
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        // Initialise firebase and setup user profile
        auth = Firebase.auth
        loadUserDetails()
        bindProfileObservers()
        bindUsersList()

        // set Empty list for recyclerView
        if (userViewModel.usersLiveData.value == null) {
            userViewModel.getUsersList(userId)
        }
        binding.usersRecyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.usersRecyclerView.adapter = usersAdapter

        // Set up button click listener
        binding.ivImage.setOnClickListener {

            galleryPermission()
        }

        binding.tvLogout.setOnClickListener(View.OnClickListener {
            FirebaseAuth.getInstance().signOut()
            Constants.switchActivities(this, LoginActivity::class.java)
        })

    }

    private fun loadUserDetails() {

        //Access current user details from below Let function
        val currentUser = auth.currentUser
        currentUser?.let {
            userId = currentUser.uid
        }
        val db = FirebaseFirestore.getInstance()
        val ref = db.collection(Constants.USERS)
        lifecycleScope.launch(Dispatchers.IO) {
            ref.document(userId).get().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val document = task.result
                    if (document.exists()) {
                        //set name and profile pic from FireStore
                        binding.tvName.setText(document.getString(Constants.NAME))
                        if (document.getString(Constants.PHOTO) != "")
                            Glide.with(binding.root).load(document.getString(Constants.PHOTO))
                                .into(binding.ivImage)
                        else
                            Glide.with(binding.root).load(R.drawable.coffee)
                                .into(binding.ivImage)
                    }
                }
            }
        }
    }

    private fun bindProfileObservers() {
        userViewModel.userProfileLiveData.observe(this) {
            // set picture to Imageview Once selected
            Glide.with(this).load(it).into(binding.ivImage)
        }
    }

    private fun bindUsersList() {
        userViewModel.usersLiveData.observe(this) {
            when (it) {
                is NetworkResult.Success -> {
                    binding.shimmerLayout.stopShimmer()
                    binding.shimmerLayout.visibility = View.GONE
                    usersAdapter.submitList(it.data)
                }

                is NetworkResult.Error -> {
                    binding.shimmerLayout.stopShimmer()
                    binding.shimmerLayout.visibility = View.GONE
                    Toast.makeText(this, "Failed to fetch users", Toast.LENGTH_SHORT).show()
                }

                is NetworkResult.Loading -> {

                }
            }
        }
    }

    private fun onItemClicked(user: UserProfile) {

        val intent = Intent(this@UsersActivity, ChatActivity::class.java)
        intent.putExtra("profile", Gson().toJson(user))
        startActivity(intent)
    }

    private fun launchImagePicker() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        getResult.launch(intent)
    }

    private val getResult =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            if (it.resultCode == Activity.RESULT_OK) {
                val value = it.data?.data
                if (value != null) {
                    userViewModel.uploadImage(value, userId)
                }
            }
        }

    private fun galleryPermission() {

        // Check permission everytime, if not granted- then request permission.
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE
            )
        } else {

            // open gallery if permission is granted.
            launchImagePicker()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE -> {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.isNotEmpty()
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED
                ) {
                    // permission was granted, do your file upload here
                    launchImagePicker()

                } else {
                    // permission denied, show a message or disable functionality
                    Snackbar.make(
                        binding.root,
                        "Please Give Storage permissions..!",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
                return
            }
        }
    }
}