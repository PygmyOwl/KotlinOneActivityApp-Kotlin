package com.example.kotlinoneactivityapp

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.doesNotExist
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility
import android.support.test.espresso.matcher.ViewMatchers.withText


class EntityProcessor {
    fun checkSymbolsOnElement (elementID: Int, textToCheck: String ) {
        onView(ViewMatchers.withId(elementID))
            .check(matches(withText(textToCheck)))
    }

    fun checkSymbolsArePresent (searchedText: String) {
        onView(withText(searchedText))
    }

    fun checkElementNotExist (searchedText: String) {
        //
        onView(withText(searchedText)).check(doesNotExist())
        //check(ViewAssertions.matches(Matchers.not(ViewMatchers.isDisplayed())))
    }

    fun checkElementNotVisible (searchedText: String) {
        onView(withText(searchedText)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.INVISIBLE)))
    }
}