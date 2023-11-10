package com.sravan.covidapplication.UIModel

import android.net.Uri
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.sravan.covidapplication.Utils.Constants
import com.sravan.covidapplication.Utils.NetworkResult
import com.sravan.covidapplication.models.UserProfile
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor() : ViewModel() {

    private val _usersLiveData = MutableLiveData<NetworkResult<ArrayList<UserProfile>>>()
    val usersLiveData get() = _usersLiveData

    private val _userprofileLiveData = MutableLiveData<String>()
    val userProfileLiveData get() = _userprofileLiveData

    private val storageRef = Firebase.storage.reference

    fun uploadImage(imageUri: Uri, userId: String) {

        val num: Int =10
        viewModelScope.launch(Dispatchers.IO) {
            val fileRef = storageRef.child("profile_pictures/$userId")
            fileRef.putFile(imageUri).addOnSuccessListener { taskSnapshot ->
                // Get the download URL of the uploaded image
                fileRef.downloadUrl
                    .addOnSuccessListener { uri ->
                        // Update the user's profile picture URL
                        userProfileLiveData.postValue(uri.toString())
                        updateImageInFireStore(uri.toString(), userId)
                    }
                    .addOnFailureListener { exception ->
                        // Handle errors
                    }
            }
                .addOnFailureListener { exception ->
                    // Handle errors
                }
        }
    }

    private fun updateImageInFireStore(uri: String, userId: String) {

        val db = Firebase.firestore
        db.collection(Constants.USERS).document(userId).update(Constants.PHOTO, uri)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.i("FIRESTORE", "Image saved successfully.")
                } else {
                    Log.i("FIRESTORE", "Image save failed.")
                }
            }
    }

    fun getUsersList(userId: String) {

        val db = FirebaseFirestore.getInstance()
        val ref = db.collection(Constants.USERS)

        viewModelScope.launch(Dispatchers.IO) {
            ref.addSnapshotListener { value, error ->
                if (error != null) {
                    // Handle error
                    usersLiveData.postValue(NetworkResult.Error("Failed to load users"))
                    return@addSnapshotListener
                }
                if (value != null) {
                    val usersList = ArrayList<UserProfile>()
                    for (data in value.documents) {
                        val user = data.toObject(UserProfile::class.java)
                        user?.let {
                            if (userId != user.uId)
                                usersList.add(it) // it is referred to the object that we are working with.
                        }
                    }
                    usersLiveData.postValue(NetworkResult.Success(usersList))
                }
            }
        }
    }
}