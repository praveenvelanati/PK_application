package com.sravan.covidapplication.room

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.sravan.covidapplication.databinding.ActivityAddTodosBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddTodosActivity : AppCompatActivity() {

    lateinit var binding: ActivityAddTodosBinding
    private val viewModel by viewModels<TodoViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddTodosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.submitButton.setOnClickListener {

            validateAndSubmit()
        }
    }

    private fun validateAndSubmit() {

        val title = binding.title.toString()
        val timeStamp = binding.timeStamp.toString()

        viewModel.insertTodo(Todo(title,timeStamp))
        finish()
    }
}