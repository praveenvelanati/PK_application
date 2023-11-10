package com.sravan.covidapplication.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.sravan.covidapplication.R
import com.sravan.covidapplication.databinding.ActivityLoaderBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class LoaderActivity : AppCompatActivity() {

    private var _binding: ActivityLoaderBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityLoaderBinding.inflate(layoutInflater)
        setContentView(binding.root)


        runBlocking {

            lifecycleScope.launch (Dispatchers.Main) {

                delay(10000)
                binding.progressBar.visibility = View.GONE

            }

            binding.progressBar.visibility = View.VISIBLE

        }

        //binding.progressBar.visibility = View.GONE

    }
}