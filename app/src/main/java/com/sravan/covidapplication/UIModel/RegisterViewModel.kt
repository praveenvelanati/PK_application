package com.sravan.covidapplication.UIModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.sravan.covidapplication.Utils.Constants
import com.sravan.covidapplication.Utils.NetworkResult
import com.sravan.covidapplication.models.UserProfile
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor() : ViewModel() {

    private val _registerLiveData = MutableLiveData<NetworkResult<String>>()
    val registerLiveData get() = _registerLiveData
    private lateinit var auth: FirebaseAuth

    fun registerUser(email: String, name: String, password: String) {

        viewModelScope.launch(Dispatchers.IO) {

            auth = FirebaseAuth.getInstance()
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task: Task<AuthResult> ->

                    if (task.isSuccessful) {
                        addDataToUser(name)
                        _registerLiveData.postValue(NetworkResult.Success("Account Created Successfully"))
                    } else {
                        _registerLiveData.postValue(NetworkResult.Error("Registration Failed with ${task.exception}"))
                    }
                }
        }
    }

    private fun addDataToUser(name: String) {

        // Once user registers, Create all necessary fields in fireStore database
        val currentUser = auth.currentUser
        val profile = currentUser?.let {
            UserProfile(
                uId = currentUser.uid,
                photoUrl = "",
                userName = name,
                emailId = currentUser.email!!,
                token = ""
            )
        }

        profile?.let {
            val db = FirebaseFirestore.getInstance()
            db.collection(Constants.USERS).document(profile.uId)
                .set(profile, SetOptions.merge())
                .addOnSuccessListener {
                    Log.d("##", "Document saved successfully")
                }
                .addOnFailureListener {
                    Log.d("##", "Document save failed")
                }
        }
    }
}