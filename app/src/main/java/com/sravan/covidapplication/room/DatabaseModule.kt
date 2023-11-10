package com.sravan.covidapplication.room

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideNotesDatabase(@ApplicationContext context: Context): NotesDatabase {
        return Room.databaseBuilder(context, NotesDatabase::class.java, "note_database")
            .build()
    }

    @Provides
    fun provideNotesDao(database: NotesDatabase): NotesDao {
        return database.getNotesDao()
    }
}