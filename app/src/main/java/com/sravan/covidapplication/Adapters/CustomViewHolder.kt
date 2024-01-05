package com.sravan.covidapplication.Adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.sravan.covidapplication.databinding.LayoutDirectorBinding
import com.sravan.covidapplication.databinding.LayoutMovieBinding
import com.sravan.covidapplication.databinding.LayoutTitleBinding
import com.sravan.covidapplication.models.CustomRecyclerViewItem

sealed class CustomViewHolder(binding: ViewBinding): RecyclerView.ViewHolder(binding.root){

    var itemClickListener: ((view: View, item: CustomRecyclerViewItem, position:Int) -> Unit)? = null

    class TitleViewHolder(private val binding: LayoutTitleBinding): CustomViewHolder(binding){

        fun bind(title: CustomRecyclerViewItem.Title){

//            itemClickListener?.invoke(it,title, layoutPosition)
        }
    }

    class DirectorViewHolder(private val binding:LayoutDirectorBinding): CustomViewHolder(binding){

        fun bind(director: CustomRecyclerViewItem.Director){

        }
    }

    class MoviesViewHolder(private val binding: LayoutMovieBinding): CustomViewHolder(binding){

        fun bind(movies: CustomRecyclerViewItem.Movies){


        }
    }
}