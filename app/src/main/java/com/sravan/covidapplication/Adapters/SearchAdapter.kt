package com.sravan.covidapplication.Adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sravan.covidapplication.R
import com.sravan.covidapplication.databinding.SearchItemBinding
import com.sravan.covidapplication.models.SearchModel.SearchCategories

class SearchAdapter(private val onItemClicked: (SearchCategories) -> Unit) : ListAdapter<SearchCategories, SearchAdapter.ViewHolder>(
        ComparatorDiffUtil()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = SearchItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val note = getItem(position)
        note?.let {
            holder.bind(it)
        }
    }

    inner class ViewHolder(private val binding: SearchItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: SearchCategories) {
            binding.storeName.text = data.name
            binding.storeDistance.text = data.distance.toString()
            Glide.with(itemView)
                .load(R.drawable.coffee)
                .into(binding.storeIcon)

            val distance = convertFeetToMiles(data.distance)
            binding.storeDistance.text = distance.toString() + " Miles Away"

            binding.root.setOnClickListener {
                onItemClicked(data)
            }
        }

    }

    class ComparatorDiffUtil : DiffUtil.ItemCallback<SearchCategories>() {
        override fun areItemsTheSame(
            oldItem: SearchCategories,
            newItem: SearchCategories
        ): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(
            oldItem: SearchCategories,
            newItem: SearchCategories
        ): Boolean {
            return oldItem == newItem
        }
    }

     fun convertFeetToMiles(distance: Int): Any {
        return distance / 5280;

    }


}