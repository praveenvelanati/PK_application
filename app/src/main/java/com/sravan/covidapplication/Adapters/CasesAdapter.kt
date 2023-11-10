package com.sravan.covidapplication.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sravan.covidapplication.models.ModelData
import com.sravan.covidapplication.databinding.DataBinding

class CasesAdapter (private val onItemClicked: (ModelData) -> Unit) : ListAdapter<ModelData, CasesAdapter.ViewHolder>(ComparatorDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val note = getItem(position)
        note?.let {
            holder.bind(it)
        }
    }

    inner class ViewHolder(private val binding: DataBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: ModelData) {
            binding.countryName.text = data.name
            binding.root.setOnClickListener {
                onItemClicked(data)
            }
        }

    }

    class ComparatorDiffUtil : DiffUtil.ItemCallback<ModelData>() {
        override fun areItemsTheSame(oldItem: ModelData, newItem: ModelData): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: ModelData, newItem: ModelData): Boolean {
            return oldItem == newItem
        }
    }
}