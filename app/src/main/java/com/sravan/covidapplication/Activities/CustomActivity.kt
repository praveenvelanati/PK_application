package com.sravan.covidapplication.Activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.sravan.covidapplication.Adapters.TownsAdapter
import com.sravan.covidapplication.UIModel.CustomViewModel
import com.sravan.covidapplication.UIModel.TownViewModel
import com.sravan.covidapplication.Utils.NetworkResult
import com.sravan.covidapplication.databinding.ActivityCustomBinding
import com.sravan.covidapplication.models.CustomRecyclerViewItem
import com.sravan.covidapplication.models.InputModel
import com.sravan.covidapplication.models.Towns
import com.sravan.covidapplication.room.NotesActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CustomActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCustomBinding
    private val viewModel by viewModels<TownViewModel>()
    private val carsViewModel by viewModels<CustomViewModel>()
    private lateinit var townsAdapter: TownsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomBinding.inflate(layoutInflater)
        townsAdapter = TownsAdapter(::OnItemClicked)
        setContentView(binding.root)

        println("This is my First Commit")
        println("This is my Second Commit")

        guhkhihijoijo

//        if (savedInstanceState == null) {

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = townsAdapter

        }

        viewModel.getTownsList(InputModel(1))

//        }

//        val fragment = BlankFragment()
//        val transaction = supportFragmentManager.beginTransaction()
//        transaction.replace(R.id.parent,fragment)
//        transaction.addToBackStack(null)
//        transaction.commit()

        bindObservers()


    }

    private fun bindObservers() {

        viewModel.townsList.observe(this) { event ->

//            event.getContentIfNotHandled()?.let {

//            val design = event.first
//            val features = event.second

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
//            }

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

