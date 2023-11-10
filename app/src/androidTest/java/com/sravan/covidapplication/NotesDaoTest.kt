package com.sravan.covidapplication

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.sravan.covidapplication.room.Note
import com.sravan.covidapplication.room.NotesDao
import com.sravan.covidapplication.room.NotesDatabase
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NotesDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    lateinit var database: NotesDatabase
    private lateinit var dao: NotesDao

    @Before
    fun setup(){

        database = Room.inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(), NotesDatabase::class.java).allowMainThreadQueries().build()
        dao = database.getNotesDao()
    }

    @Test
    fun testInsertNotes() = runTest() {

        val notes = Note("First", "This is my first Note","10-27-2023: 01:45 PM", 1)
        dao.insert(notes)

        val notesList = dao.getAllNotes().getOrAwaitValue()
        assertThat(notesList).contains(notes)

    }

    @Test
    fun testInsertNotesWithSameID() = runTest() {

        val notes = Note("First", "This is my first Note","10-27-2023: 01:45 PM", 1)
        val notes2 = Note("Second"," This is my second note", "10-27-2023: 05:05 PM",1)

        dao.insert(notes)
        dao.insert(notes2)

        val notesList = dao.getAllNotes().getOrAwaitValue()
        Assert.assertEquals(notes, notesList[0])

    }

    @Test
    fun testUpdateNoteReturnsTrue() = runTest {

        val notes = Note("Second"," This is my second note", "10-27-2023: 05:05 PM",1)
        dao.insert(notes)

        val updateNote = notes.copy("Updated Second"," This is my updated Second note", "10-27-2023: 06:00 PM", 1)
        dao.update(updateNote)

        val retrievedNote = dao.getNoteById(notes.id)
        Assert.assertEquals(updateNote, retrievedNote)
    }

    @Test
    fun testDeleteNoteReturnsTrue() = runTest{

        val notes = Note("Second"," This is my second note", "10-27-2023: 05:05 PM",1)
        dao.insert(notes)
        dao.delete(notes)

        val notesList = dao.getAllNotes().getOrAwaitValue()
        assertThat(notesList).doesNotContain(notes)

    }

    @Test
    fun testCheckNumberOfNotesReturnsTrue() = runTest {

        val notes1 = Note("First", "This is my first Note","10-27-2023: 01:45 PM", 1)
        val notes2 = Note("Second"," This is my second note", "10-27-2023: 05:05 PM",2)
        val notes3 = Note("Second"," This is my second note", "10-27-2023: 05:05 PM",3)

        dao.insert(notes1)
        dao.insert(notes2)
        dao.insert(notes3)

        val notesList = dao.getAllNotes().getOrAwaitValue()
        assertThat(notesList).hasSize(3)

    }

    @After
    fun tearDown(){

        database.close()
    }
}