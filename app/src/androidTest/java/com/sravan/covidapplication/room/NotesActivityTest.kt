package com.sravan.covidapplication.room

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withHint
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.sravan.covidapplication.R
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NotesActivityTest{

    @Before
    fun setup(){

        ActivityScenario.launch(NotesActivity::class.java)
    }

    @Test
    fun test_if_floatingButtonIsVisible(){

        onView(withId(R.id.idFAB)).check(matches(isDisplayed()))
    }

    @Test
    fun test_if_activityOpens_onFloatingButtonCLick(){

        onView(withId(R.id.idFAB)).perform(click())
        onView(withId(R.id.idEdtNoteName)).check(matches(withHint(R.string.title_hint)))

    }

    @Test
    fun test_the_complete_flow(){

        onView(withId(R.id.idFAB)).perform(click())
        onView(withId(R.id.idEdtNoteName)).perform(typeText("Android Developer"))
        onView(withId(R.id.idEdtNoteDesc)).perform(typeText("My First Note"))
        Espresso.closeSoftKeyboard()
        onView(withId(R.id.idBtn)).perform(click())
        onView(withId(R.id.idTVNote)).check(matches(withText("Android Developer")))
    }
}