package com.sravan.covidapplication.Activities

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.messaging.FirebaseMessaging
import com.sravan.covidapplication.UIModel.LoginViewModel
import com.sravan.covidapplication.Utils.Constants
import com.sravan.covidapplication.Utils.NetworkResult
import com.sravan.covidapplication.Utils.SharedPreferenceHelper
import com.sravan.covidapplication.Utils.showSnackBar
import com.sravan.covidapplication.Utils.toast
import com.sravan.covidapplication.databinding.ActivityLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.runBlocking

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private var _binding: ActivityLoginBinding? = null
    private val binding get() = _binding!!
    private val loginViewModel by viewModels<LoginViewModel>()
    private lateinit var sharedPref: SharedPreferenceHelper
    private lateinit var fcmToken: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Get fcm token everytime user launches the app with new login
        getFCMToken()

        binding.tvLogin.setOnClickListener(View.OnClickListener {
            Constants.toggleViews(binding.progressBar, binding.tvLogin)
            validateInputs()
        })

        binding.tvSignUp.setOnClickListener(View.OnClickListener {
            Constants.switchActivities(this, RegisterActivity::class.java)
        })

        binding.tvForgotPassword.setOnClickListener(View.OnClickListener {
            showSnackBar(binding.root, "Coming soon")
        })

        updateUI()

    }

    // practice func (Extension function)
    fun String.compareTo(word: String, ignore:Boolean): Int{

        return 0
    }

    private fun validateInputs() {

        val email = Constants.getTextFromEdittext(binding.etEmail)
        val password = Constants.getTextFromEdittext(binding.etPassword)

        if ((email.isEmpty() && !Constants.isValidEmail(email)) || password.isEmpty()) {
            Constants.toggleViews(binding.tvLogin, binding.progressBar)
            toast("All Fields are required")
            return
        }
        //If all the Input fields are good, We proceed.
        // Initialising auth object
        loginViewModel.userLogin(email, password)
    }

    private fun updateUI() {

        loginViewModel.loginLiveData.observe(this) {

            when (it) {
                is NetworkResult.Success -> {
                    Constants.switchActivities(this, UsersActivity::class.java)
                    val userId = FirebaseAuth.getInstance().currentUser?.uid?: ""
                    loginViewModel.updateFcmTokenToFireStore(fcmToken,userId)
                }
                is NetworkResult.Error -> {
                    Constants.toggleViews(binding.tvLogin, binding.progressBar)
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
                is NetworkResult.Loading -> {
                    // Show loader if you have one
                }
            }
        }
    }

    private fun getFCMToken() {
        runBlocking {
            FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
                if (!task.isSuccessful) {
                    // Handle any errors that occur
                    Log.e("FCM", fcmToken)
                    return@addOnCompleteListener
                }
                // Get the FCM token
                fcmToken = task.result.toString()
                Log.e("FCM", fcmToken)
            }
        }
    }

}