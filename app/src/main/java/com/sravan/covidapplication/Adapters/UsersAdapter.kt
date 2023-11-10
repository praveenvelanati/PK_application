package com.sravan.covidapplication.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sravan.covidapplication.databinding.UsersBinding
import com.sravan.covidapplication.models.UserProfile

class UsersAdapter(private val onItemClicked: (UserProfile) -> Unit): ListAdapter<UserProfile, UsersAdapter.ViewHolder>(ComparatorDiffUtil()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding = UsersBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val note = getItem(position)
        note?.let {
            holder.bind(it)
        }
    }

    inner class ViewHolder(private val binding: UsersBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(data: UserProfile){
            binding.tvName.setText(data.userName)
            binding.tvLatestMessage.setText("Start texting now")
            binding.tvTime.setText("2:10 PM")
            Glide.with(binding.root).load(data.photoUrl).into(binding.ivImage)
        }
    }

    class ComparatorDiffUtil : DiffUtil.ItemCallback<UserProfile>() {
        override fun areItemsTheSame(oldItem: UserProfile, newItem: UserProfile): Boolean {
            return oldItem.uId == newItem.uId
        }

        override fun areContentsTheSame(oldItem: UserProfile, newItem: UserProfile): Boolean {
            return oldItem == newItem
        }
    }
}
