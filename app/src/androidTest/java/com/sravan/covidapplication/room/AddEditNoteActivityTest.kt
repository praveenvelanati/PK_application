package com.sravan.covidapplication.room

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
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
class AddEditNoteActivityTest{


    @Before
    fun setup(){

        ActivityScenario.launch(AddEditNoteActivity::class.java)
    }

    @Test
    fun testEditTextIfHintIsDisplayed(){

        onView(withId(R.id.idEdtNoteName)).check(matches(withHint(R.string.title_hint)))
    }

    @Test
    fun test_if_empty_edittext_shows_error_message(){

        onView(withId(R.id.idBtn)).perform(click())
        onView(withId(R.id.tv_titleError)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_titleError)).check(matches(withText(R.string.title_error)))
    }

}