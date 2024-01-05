package com.sravan.covidapplication.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sravan.covidapplication.databinding.TownItemBinding
import com.sravan.covidapplication.models.Towns

class TownsAdapter(private val onItemClicked: (Towns) -> Unit) :
    RecyclerView.Adapter<TownsAdapter.ViewHolder>() {

    var townsList = listOf<Towns>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding = TownItemBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = townsList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = townsList[position]
        holder.bind(item)
    }

    inner class ViewHolder(private val itemBinding: TownItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(item: Towns) {
            itemBinding.tvTown.text = item.name
            itemBinding.tvStatus.text = item.status

            itemBinding.linearlayout.setOnClickListener {

                onItemClicked.invoke(item)
            }
        }
    }
}