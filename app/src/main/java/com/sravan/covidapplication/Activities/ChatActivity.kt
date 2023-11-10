package com.sravan.covidapplication.Activities

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import com.google.firebase.auth.FirebaseAuth
import com.google.gson.Gson
import com.sravan.covidapplication.Fragments.ChatFragment
import com.sravan.covidapplication.R
import com.sravan.covidapplication.databinding.ActivityChatBinding
import com.sravan.covidapplication.models.UserProfile

class ChatActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        // getting userData from previous activity
        val data = intent.getStringExtra("DATA")
        val userData = Gson().toJson(data, UserProfile::class.java)

        // calling chat fragment with bundle of data
        val bundle = Bundle().apply {
            putString("DATA",Gson().toJson(userData))
        }
        val chatFragment = ChatFragment()
        chatFragment.arguments = bundle
        supportFragmentManager.beginTransaction().replace(R.id.container,chatFragment).commit()

    }
}