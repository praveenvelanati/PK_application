package com.sravan.covidapplication.room

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class TodoModule {

    @Singleton
    @Provides
    fun providesTodoDatabase(@ApplicationContext context: Context): TodoDatabase{

        return Room.databaseBuilder(context,TodoDatabase::class.java,"todo_list").build()
    }

    @Singleton
    @Provides
    fun providesTodoDao(todoDatabase: TodoDatabase): TodoDao{

        return todoDatabase.getTodoDao()
    }
}