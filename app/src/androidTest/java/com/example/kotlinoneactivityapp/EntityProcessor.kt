package com.example.kotlinoneactivityapp

import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.assertion.ViewAssertions.doesNotExist
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility
import android.support.test.espresso.matcher.ViewMatchers.withText

class EntityProcessor {
    public fun checkSymbolsOnElement (elementID: Int, textToCheck: String ) {
        Espresso.onView(ViewMatchers.withId(elementID))
            .check(ViewAssertions.matches(ViewMatchers.withText(textToCheck)))
    }

    public fun checkSymbolsArePresent (searchedText: String) {
        onView(withText(searchedText))
    }

    public fun checkElementNotExist (searchedText: String) {
        //
        onView(withText(searchedText)).check(doesNotExist())
        //check(ViewAssertions.matches(Matchers.not(ViewMatchers.isDisplayed())))
    }

    public fun checkElementNotVisible (searchedText: String) {
        onView(withText(searchedText)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.INVISIBLE)))
    }
}