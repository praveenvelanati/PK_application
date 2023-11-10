package com.sravan.covidapplication.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import com.sravan.covidapplication.R
import com.sravan.covidapplication.databinding.ActivityDetailsBinding
import com.sravan.covidapplication.models.ModelData

class DetailsActivity : AppCompatActivity() {

    private var _binding: ActivityDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding  = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val url = intent.getStringExtra("data")

        binding.webView.settings.javaScriptEnabled = true

        if (url != null) {

            binding.webView.webViewClient = WebViewClient()
            binding.webView.loadUrl(url)
        }


    }
}