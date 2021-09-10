package com.example.kotlinoneactivityapp

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.ViewInteraction
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText

class EntityInteractor {
    private lateinit var button : ViewInteraction

    fun clickOnButtonWithID (buttonID: Int) {
        button = onView(withId(buttonID))
        button.perform(click())
    }

    fun clickOnButtonWithText (buttonText: String) {
        button = onView(withText(buttonText))
        button.perform(click())
    }
}