package com.sravan.covidapplication.room

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.sravan.covidapplication.Fragments.ChatFragment
import com.sravan.covidapplication.R
import com.sravan.covidapplication.databinding.ActivityTodoBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TodoActivity : AppCompatActivity() {

    lateinit var binding: ActivityTodoBinding
    lateinit var todoAdapter: TodoAdapter
    private val viewModel by viewModels<TodoViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTodoBinding.inflate(layoutInflater)
        todoAdapter = TodoAdapter(::OnItemClick, ::onItemDeleteClick)
        setContentView(binding.root)

        binding.floatingButton.setOnClickListener {

            val intent = Intent(this, AddTodosActivity::class.java)
            startActivity(intent)
        }

        binding.todoRecyclerView.apply {

            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = todoAdapter

        }

        viewModel.todoList.observe(this) {

            it?.let {

                todoAdapter.todoList = it
            }
        }

    }

    private fun OnItemClick(todo: Todo) {

        val fragment = ChatFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.apply() {

            replace(R.id.parent, fragment)
            addToBackStack(null)
            commit()
        }
    }

    private fun onItemDeleteClick(todo: Todo) {

    }


}