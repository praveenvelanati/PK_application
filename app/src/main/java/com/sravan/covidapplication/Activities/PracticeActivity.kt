package com.sravan.covidapplication.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.sravan.covidapplication.Adapters.EntryAdapter
import com.sravan.covidapplication.Adapters.SearchAdapter
import com.sravan.covidapplication.R
import com.sravan.covidapplication.UIModel.CasesViewModel
import com.sravan.covidapplication.UIModel.EntryViewModel
import com.sravan.covidapplication.Utils.NetworkResult
import com.sravan.covidapplication.Utils.toast
import com.sravan.covidapplication.databinding.ActivityPracticeBinding
import com.sravan.covidapplication.databinding.DataBinding
import com.sravan.covidapplication.models.EntryData
import com.sravan.covidapplication.models.SearchModel.SearchCategories
import dagger.hilt.android.AndroidEntryPoint

class PracticeActivity : AppCompatActivity() {

    private var _binding: ActivityPracticeBinding? = null
    private val binding get() = _binding!!
    private val entryViewModel by viewModels<EntryViewModel>()
    private lateinit var entryAdapter: EntryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityPracticeBinding.inflate(layoutInflater)

        entryAdapter = EntryAdapter(::onItemClicked)
        setContentView(binding.root)

        entryViewModel.getData()
        binding.practiceRecyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.practiceRecyclerView.adapter = entryAdapter

        entryViewModel.entryLiveData.observe(this){

            when(it){

                is NetworkResult.Success -> {
                    entryAdapter.submitList(it.data!!.entries)
                }
                else -> {
                    toast("Something went wrong..!")

                }
            }
        }
        entryViewModel.getData()

    }

    fun onItemClicked(entryData: EntryData) {
        toast("Something went wrong..!")

    }
}