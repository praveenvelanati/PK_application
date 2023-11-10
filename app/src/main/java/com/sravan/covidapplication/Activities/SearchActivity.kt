package com.sravan.covidapplication.Activities

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.sravan.covidapplication.Adapters.SearchAdapter
import com.sravan.covidapplication.UIModel.SearchViewModel
import com.sravan.covidapplication.Utils.NetworkResult
import com.sravan.covidapplication.Utils.toast
import com.sravan.covidapplication.databinding.ActivitySearchBinding
import com.sravan.covidapplication.models.SearchModel.SearchCategories

class SearchActivity : AppCompatActivity() {

    private var _binding: ActivitySearchBinding? = null
    private val binding get() = _binding!!
    private val searchViewModel by viewModels<SearchViewModel>()
    private lateinit var searchAdapter: SearchAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySearchBinding.inflate(layoutInflater)
        searchAdapter = SearchAdapter(::onItemClicked)
        setContentView(binding.root)

        binding.searchRecyclerView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        binding.searchRecyclerView.adapter = searchAdapter

        binding.searchBar.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                if (s?.length!! >3){

                    var str = s.toString()
                    searchViewModel.getSearchResults(str)

                }
            }
        })

        bindObservers()
    }

    private fun bindObservers() {

        searchViewModel.searchLiveData.observe(this){

            binding.progressBar.visibility = View.VISIBLE
            when(it){

                is NetworkResult.Success -> {

                    binding.progressBar.visibility = View.GONE
                    searchAdapter.submitList(it.data?.results)
                }
                is NetworkResult.Error -> {

                    binding.progressBar.visibility = View.GONE
                    toast("Something went wrong..!")
                }
                is NetworkResult.Loading -> {

                    // still progressbar
                    binding.progressBar.visibility = View.GONE
                }

            }
        }

    }


    private fun onItemClicked(data: SearchCategories) {

        val intent = Intent(this@SearchActivity, DetailsActivity::class.java)
        intent.putExtra("data", "https://www.google.com/maps")
        startActivity(intent)

    }
}