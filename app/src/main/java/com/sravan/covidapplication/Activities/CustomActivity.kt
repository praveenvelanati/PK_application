package com.sravan.covidapplication.Activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.sravan.covidapplication.Adapters.TownsAdapter
import com.sravan.covidapplication.UIModel.CustomViewModel
import com.sravan.covidapplication.UIModel.SampleViewModel
import com.sravan.covidapplication.UIModel.TownViewModel
import com.sravan.covidapplication.Utils.NetworkResult
import com.sravan.covidapplication.databinding.ActivityCustomBinding
import com.sravan.covidapplication.models.CustomRecyclerViewItem
import com.sravan.covidapplication.models.Towns
import com.sravan.covidapplication.room.NotesActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CustomActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCustomBinding
    private val viewModel by viewModels<TownViewModel>()
    private val carsViewModel by viewModels<CustomViewModel>()
    private val userViewModel by viewModels<SampleViewModel>()
    private lateinit var townsAdapter: TownsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomBinding.inflate(layoutInflater)
        townsAdapter = TownsAdapter(::OnItemClicked)
        setContentView(binding.root)

//        binding.recyclerView.apply {
//            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
//            adapter = townsAdapter
//
//        }

//        viewModel.getTownsList(InputModel(1))

//        bindObservers()

        userObserver()

    }

    private fun userObserver() {

        lifecycleScope.launch {

            repeatOnLifecycle(Lifecycle.State.STARTED){

                userViewModel.content.collect {

                    when(it){

                        is NetworkResult.Success -> {

                            binding.progressBar.visibility = View.GONE
                            Toast.makeText(this@CustomActivity, it.data?.size.toString(), Toast.LENGTH_SHORT).show()
                        }

                        is NetworkResult.Error -> {

                            binding.progressBar.visibility = View.GONE
                            Toast.makeText(this@CustomActivity, it.message, Toast.LENGTH_SHORT).show()

                        }

                        is NetworkResult.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                        }
                    }
                }
            }

        }
    }

    private fun bindObservers() {

        viewModel.townsList.observe(this) { event ->

            when (event) {
                is NetworkResult.Error -> Toast.makeText(this, "Error", Toast.LENGTH_SHORT)
                    .show()

                is NetworkResult.Loading -> Toast.makeText(this, "Loading", Toast.LENGTH_SHORT)
                    .show()

                is NetworkResult.Success -> {

                    Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
                    townsAdapter.townsList = event.data!!

                }
            }
        }
    }

    private fun OnItemClicked(towns: Towns) {

        val intent = Intent(this, NotesActivity::class.java)
        startActivity(intent)
    }


    fun getData(): MutableList<CustomRecyclerViewItem> {

        val dataList = mutableListOf<CustomRecyclerViewItem>().apply {

            add(CustomRecyclerViewItem.Title(1, "Sravan"))
            add(CustomRecyclerViewItem.Title(2, "Android"))

            add(CustomRecyclerViewItem.Movies(1, "Sravan", "sdkd", "djbjbd"))

        }

        return dataList
    }
}

