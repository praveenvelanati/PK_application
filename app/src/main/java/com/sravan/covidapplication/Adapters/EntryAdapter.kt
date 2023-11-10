package com.sravan.covidapplication.Adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sravan.covidapplication.databinding.DataBinding
import com.sravan.covidapplication.models.EntryData

class EntryAdapter(private val onItemClicked: (EntryData) -> Unit) : ListAdapter<EntryData, EntryAdapter.ViewHolder>(ComparatorDiffUtil()) {

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

        fun bind(data: EntryData) {
           binding.countryName.text = data.API
            binding.lastUpdated.text = data.Description
            binding.root.setOnClickListener {
                onItemClicked(data)
            }
        }

    }

    class ComparatorDiffUtil : DiffUtil.ItemCallback<EntryData>() {
        override fun areItemsTheSame(oldItem: EntryData, newItem: EntryData): Boolean {
            return oldItem.API == newItem.API
        }

        override fun areContentsTheSame(oldItem: EntryData, newItem: EntryData): Boolean {
            return oldItem == newItem
        }
    }
}