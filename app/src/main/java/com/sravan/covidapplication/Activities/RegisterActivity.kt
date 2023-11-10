package com.sravan.covidapplication.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.sravan.covidapplication.R
import com.sravan.covidapplication.UIModel.RegisterViewModel
import com.sravan.covidapplication.Utils.Constants
import com.sravan.covidapplication.Utils.NetworkResult
import com.sravan.covidapplication.Utils.toast
import com.sravan.covidapplication.databinding.ActivityRegisterBinding
import dagger.hilt.android.AndroidEntryPoint

class RegisterActivity : AppCompatActivity() {

    private var _binding: ActivityRegisterBinding? = null
    private val binding get() = _binding!!
    private val registerViewModel by viewModels<RegisterViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvSignUp.setOnClickListener(View.OnClickListener {

            Constants.toggleViews(binding.progressBar, binding.tvSignUp)
            validateAndProceed(binding)
        })

        binding.ivBack.setOnClickListener(View.OnClickListener {
            Constants.switchActivities(this, LoginActivity::class.java)

        })

        updateUI()

    }

    private fun validateAndProceed(binding: ActivityRegisterBinding) {

        val email = Constants.getTextFromEdittext(binding.etEmail)
        val name = Constants.getTextFromEdittext(binding.etName)
        val password = Constants.getTextFromEdittext(binding.etPassword)
        val cPassword = Constants.getTextFromEdittext(binding.etConfirmPassword)

        if ((email.isEmpty() && !Constants.isValidEmail(email)) || name.isEmpty() || password.isEmpty() || cPassword.isEmpty()) {
            Constants.toggleViews(binding.tvSignUp, binding.progressBar)
            toast("All Fields are required")
            return
        }

        if ((password != cPassword)) {
            Constants.toggleViews(binding.tvSignUp, binding.progressBar)
            toast("Password and Confirm Password do not match")
            return
        }

        //If all the Input fields are good, We proceed.
        // Initialising auth object
        registerViewModel.registerUser(email, name, password)

    }

    private fun updateUI() {

        registerViewModel.registerLiveData.observe(this) {

            when (it) {

                is NetworkResult.Success -> {
                    Snackbar.make(binding.root, "Registration Successful.!", Snackbar.LENGTH_INDEFINITE)
                        .setAction("OK") {
                            Constants.switchActivities(this, LoginActivity::class.java)
                        }.setTextColor(ContextCompat.getColor(this, R.color.maintwo))
                        .setBackgroundTint(ContextCompat.getColor(this, R.color.White))
                        .setActionTextColor(ContextCompat.getColor(this, R.color.maintwo))
                        .show()
                }
                is NetworkResult.Error -> {
                    Constants.toggleViews(binding.tvSignUp, binding.progressBar)
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
                is NetworkResult.Loading -> {
                    //hide progress bar if you have one
                }
            }
        }
    }
}