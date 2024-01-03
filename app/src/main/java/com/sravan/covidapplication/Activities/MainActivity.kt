package com.sravan.covidapplication.Activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.sravan.covidapplication.Adapters.CasesAdapter
import com.sravan.covidapplication.UIModel.CasesViewModel
import com.sravan.covidapplication.Utils.NetworkResult
import com.sravan.covidapplication.Utils.toast
import com.sravan.covidapplication.databinding.ActivityMainBinding
import com.sravan.covidapplication.models.ModelData
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private val caseViewModels by viewModels<CasesViewModel>()
    private lateinit var casesAdapter: CasesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        casesAdapter = CasesAdapter(::onItemClicked)
        setContentView(binding.root)

        println("first commit")
        println("T101")

        caseViewModels.getAllCases()
        binding.casesRecyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.casesRecyclerView.adapter = casesAdapter


        bindObservers()

    }

    private fun bindObservers() {

        caseViewModels.casesLiveData.observe(this) {
            //setProgressBar

            when (it) {

                is NetworkResult.Success -> {
                    casesAdapter.submitList(it.data)
                }

                is NetworkResult.Error -> {

                    toast("Something went wrong..!")
                }
                is NetworkResult.Loading -> {

                    // still progressbar
                }

            }
        }
    }

    private fun onItemClicked(modelData: ModelData) {

        val intent = Intent(this@MainActivity, DetailsActivity::class.java)
        intent.putExtra("data", modelData.web_pages.get(0))
        startActivity(intent)

    }

}