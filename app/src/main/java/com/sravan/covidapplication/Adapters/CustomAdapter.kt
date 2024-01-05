package com.sravan.covidapplication.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sravan.covidapplication.R
import com.sravan.covidapplication.databinding.LayoutDirectorBinding
import com.sravan.covidapplication.databinding.LayoutMovieBinding
import com.sravan.covidapplication.databinding.LayoutTitleBinding
import com.sravan.covidapplication.models.CustomRecyclerViewItem

class CustomAdapter : RecyclerView.Adapter<CustomViewHolder>() {

    var items = listOf<CustomRecyclerViewItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var itemClickListener: ((view:View, item: CustomRecyclerViewItem, position: Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {

       return when (viewType) {

            R.layout.layout_title -> CustomViewHolder.TitleViewHolder(
                LayoutTitleBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )

            R.layout.layout_movie -> CustomViewHolder.MoviesViewHolder(
                LayoutMovieBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )

            R.layout.layout_director -> CustomViewHolder.DirectorViewHolder(
                LayoutDirectorBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )

            else -> throw IllegalArgumentException("Invalid ViewType Provided")
        }

    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {

        holder.itemClickListener = itemClickListener

        when(holder){
            is CustomViewHolder.DirectorViewHolder -> holder.bind(items[position] as CustomRecyclerViewItem.Director)
            is CustomViewHolder.MoviesViewHolder -> holder.bind(items[position] as CustomRecyclerViewItem.Movies)
            is CustomViewHolder.TitleViewHolder -> holder.bind(items[position] as CustomRecyclerViewItem.Title)
        }
    }

    override fun getItemViewType(position: Int): Int {

        return when (items[position]) {
            is CustomRecyclerViewItem.Director -> R.layout.layout_director
            is CustomRecyclerViewItem.Movies -> R.layout.layout_movie
            is CustomRecyclerViewItem.Title -> R.layout.layout_title
        }

    }
}