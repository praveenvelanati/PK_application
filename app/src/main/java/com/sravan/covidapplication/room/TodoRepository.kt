package com.sravan.covidapplication.room

import androidx.lifecycle.LiveData
import javax.inject.Inject

class TodoRepository @Inject constructor(private val todoDao: TodoDao){

    val todoList: LiveData<List<Todo>> = todoDao.getAllTodo()

    suspend fun insertTodo(todo: Todo){

        todoDao.insertTodo(todo)
    }

    suspend fun deleteTodo(todo: Todo){

        todoDao.deleteTodo(todo)
    }
}