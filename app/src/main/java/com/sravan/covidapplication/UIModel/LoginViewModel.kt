package com.sravan.covidapplication.UIModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.sravan.covidapplication.Utils.Constants
import com.sravan.covidapplication.Utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {

    private val _loginLiveData = MutableLiveData<NetworkResult<String>>()
    val loginLiveData get() = _loginLiveData
    private lateinit var auth: FirebaseAuth

    fun userLogin(email: String, password: String) {

        viewModelScope.launch(Dispatchers.IO) {
            auth = FirebaseAuth.getInstance()
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task: Task<AuthResult> ->

                    if (task.isSuccessful) {
                        _loginLiveData.postValue(NetworkResult.Success("Login Successful"))
                    } else {
                        _loginLiveData.postValue(NetworkResult.Error("Something went wrong ${task.exception}"))
                    }

                }
        }
    }

    fun updateFcmTokenToFireStore(fcmToken: String, userId: String) {

        val db = Firebase.firestore
        val userRef = db.collection(Constants.USERS).document(userId)

        viewModelScope.launch {
            userRef.update("token", fcmToken).addOnSuccessListener {
                Log.d("FCM", "FCM token updated successfully")

            }.addOnFailureListener {
                Log.e("FCM", "Error updating FCM token")
            }
        }
    }
}