package com.sravan.covidapplication.room

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sravan.covidapplication.databinding.TodoItemBinding

class TodoAdapter(
    private val onItemClick: (Todo) -> Unit,
    private val onItemDeleteClick: (Todo) -> Unit
) : RecyclerView.Adapter<TodoAdapter.ViewHolder>() {

    var todoList = listOf<Todo>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding = TodoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TodoAdapter.ViewHolder, position: Int) {

        val item = todoList[position]
        holder.bind(item)

    }

    override fun getItemCount() = todoList.size

    inner class ViewHolder(private val binding: TodoItemBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(item: Todo){

            binding.todoTitle.text = item.title
            binding.todoTime.text = item.timeStamp

            binding.todoTitle.setOnClickListener {

                onItemClick.invoke(item)
            }

            binding.deleteIcon.setOnClickListener {

                onItemDeleteClick.invoke(item)
            }
        }
    }

}