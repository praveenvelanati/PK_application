package com.sravan.covidapplication.room

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(private val repository: TodoRepository): ViewModel() {

    val todoList: LiveData<List<Todo>> = repository.todoList

    fun insertTodo(todo: Todo) = viewModelScope.launch(Dispatchers.IO) {

        repository.insertTodo(todo)
    }

    fun deleteTodo(todo: Todo) = viewModelScope.launch(Dispatchers.IO) {

        repository.deleteTodo(todo)
    }
}