package com.sravan.covidapplication.room

import androidx.lifecycle.LiveData
import javax.inject.Inject

class NoteRepository @Inject constructor( private val notesDao: NotesDao) {

    val allNotes: LiveData<List<Note>> = notesDao.getAllNotes()

    suspend fun insert(note: Note){

        notesDao.insert(note)
    }

    suspend fun delete(note: Note){

        notesDao.delete(note)
    }

    suspend fun update(note: Note){

        notesDao.update(note)
    }
}