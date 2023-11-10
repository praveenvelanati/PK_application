package com.sravan.covidapplication.room

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.sravan.covidapplication.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotesActivity : AppCompatActivity(), NoteClickInterface, NoteClickDeleteInterface {

    private val noteViewModels by viewModels<NoteViewModel>()
    private lateinit var notesAdapter: NoteRVAdapter
    lateinit var notesRV: RecyclerView
    lateinit var addFAB: FloatingActionButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes)

        // on below line we are initializing
        // all our variables.
        notesRV = findViewById(R.id.notesRV)
        addFAB = findViewById(R.id.idFAB)

        notesRV.layoutManager = LinearLayoutManager(this)
        notesAdapter = NoteRVAdapter(this,this,this)
        notesRV.adapter = notesAdapter

        noteViewModels.allNotes.observe(this, Observer { list ->

            list?.let {
                notesAdapter.updateList(list)
            }
        })

        addFAB.setOnClickListener {
            // adding a click listener for fab button
            // and opening a new intent to add a new note.
            val intent = Intent(this, AddEditNoteActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onNoteClick(note: Note) {
        // opening a new intent and passing a data to it.
        val intent = Intent(this, AddEditNoteActivity::class.java)
        intent.putExtra("noteType", "Edit")
        intent.putExtra("noteTitle", note.noteTitle)
        intent.putExtra("noteDescription", note.notesDescription)
        intent.putExtra("noteId", note.id)
        startActivity(intent)
    }

    override fun onDeleteIconClick(note: Note) {
        // in on note click method we are calling delete
        // method from our view modal to delete our not.
        noteViewModels.deleteNote(note)
        // displaying a toast message
        Toast.makeText(this, "${note.noteTitle} Deleted", Toast.LENGTH_LONG).show()
    }
}