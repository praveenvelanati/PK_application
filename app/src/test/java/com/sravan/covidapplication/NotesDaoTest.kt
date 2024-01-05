package com.sravan.covidapplication

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.sravan.covidapplication.Utils.getOrAwaitValue
import com.sravan.covidapplication.room.Note
import com.sravan.covidapplication.room.NoteRepository
import com.sravan.covidapplication.room.NotesDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class NotesDaoTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var notesDaoMock: NotesDao
    private lateinit var notesRepository: NoteRepository
    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setup(){

        Dispatchers.setMain(testDispatcher)
        notesDaoMock = mock(NotesDao::class.java)
        notesRepository = NoteRepository(notesDaoMock)

    }

    @Test
    fun testInsertNotes() = runTest{

        val notes = listOf(Note("Sravan", " Android Developer", "Today"), Note("Velanati", " Android Developer", "Tomorrow"))
        val noteList = MutableLiveData<List<Note>>()
        noteList.value = notes

        Mockito.`when`(notesRepository.allNotes).thenReturn(noteList)

        val result = notesDaoMock.getAllNotes().getOrAwaitValue()
        Assert.assertEquals(noteList.value, result)

    }

}