package com.sravan.covidapplication.room

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor( private val noteRepository: NoteRepository): ViewModel() {

    val allNotes = noteRepository.allNotes

    fun insertNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {

        noteRepository.insert(note)
    }

    fun updateNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        noteRepository.update(note)
    }

    fun deleteNote(note: Note) = viewModelScope.launch (Dispatchers.IO){
        noteRepository.delete(note)
    }
}