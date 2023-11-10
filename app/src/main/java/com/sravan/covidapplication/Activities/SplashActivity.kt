package com.sravan.covidapplication.Activities

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.animation.AnimationUtils
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.sravan.covidapplication.R
import com.sravan.covidapplication.Utils.Constants
import com.sravan.covidapplication.databinding.ActivitySplashBinding

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private var _binding: ActivitySplashBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //setup UI
        Glide.with(this).load(R.drawable.splash).into(binding.ivSplash)
        val animationFadeIn = AnimationUtils.loadAnimation(this, R.anim.bounce)
        binding.tvName.startAnimation(animationFadeIn)

        // Handler().postDelayed({
        Handler(Looper.getMainLooper()).postDelayed({
            auth = FirebaseAuth.getInstance()
            if (auth.getCurrentUser() != null)
            //user logged in already, do your work here for logged in user
                Constants.switchActivities(this, UsersActivity::class.java)
            else
            //user is not logged in, let user login
                Constants.switchActivities(this, LoginActivity::class.java)
        }, 2500) // 3000 is the delayed time in milliseconds.

    }
}