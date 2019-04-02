package com.example.kotlinoneactivityapp


import android.util.Log
import android.widget.TextView

import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

import android.support.test.runner.AndroidJUnit4
import android.support.test.rule.ActivityTestRule
import android.support.test.espresso.ViewInteraction
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText

@RunWith(AndroidJUnit4::class)

class MainActivityTest {

    var numFromTV = 0

    @get:Rule
    var mActivityTestRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Test
    fun plusThreeTest() {
        val plusThreeAButton: ViewInteraction = onView(withId(R.id.plus3Abutton))
        plusThreeAButton.perform(click())
        onView(withId(R.id.TeamAScore)).check(matches(withText("3")))

        val plusThreeBButton: ViewInteraction = onView(withId(R.id.plus3Bbutton))
        plusThreeBButton.perform(click())
        onView(withId(R.id.TeamBScore)).check(matches(withText("3")))
    }

    @Test
    fun plusTwoTest() {
        val plusThreeAButton: ViewInteraction = onView(withId(R.id.plus2Abutton))
        plusThreeAButton.perform(click())
        onView(withId(R.id.TeamAScore)).check(matches(withText("2")))

        val plusThreeBButton: ViewInteraction = onView(withId(R.id.plus2Bbutton))
        plusThreeBButton.perform(click())
        onView(withId(R.id.TeamBScore)).check(matches(withText("2")))
    }

    @Test
    fun resetScoreTest() {
        val resetButton = onView(withId(R.id.resetButton))
        resetButton.perform(click())
        onView(withId(R.id.TeamAScore)).check(matches(withText("0")))
        onView(withId(R.id.TeamBScore)).check(matches(withText("0")))
    }

    @Test
    fun checkFreeAThrow() {
        var numBeforeClick: Int
        var numAfterClick: Int
        var freeThrowPoints: Int
        for (i in 0..30) {
            getNumFromTV(1)
            numBeforeClick = numFromTV
            val freeThrowButton = onView(withId(R.id.freeBthrow))
            freeThrowButton.perform(click())
            getNumFromTV(1)
            numAfterClick = numFromTV
            freeThrowPoints = numAfterClick - numBeforeClick
            Log.d("MyApp", "" + freeThrowPoints)
            if (freeThrowPoints < 0 && freeThrowPoints > 3) {
                onView(withId(R.id.TeamBScore)).check(matches(withText("0")))
            }
        }
    }

    @Test
    fun checkFreeBThrow() {
        var numBeforeClick: Int
        var numAfterClick: Int
        var freeThrowPoints: Int
        for (i in 0..30) {
            getNumFromTV(2)
            numBeforeClick = numFromTV
            val freeThrowButton = onView(withId(R.id.freeAthrow))
            freeThrowButton.perform(click())
            getNumFromTV(2)
            numAfterClick = numFromTV
            freeThrowPoints = numAfterClick - numBeforeClick
            Log.d("MyApp", "" + freeThrowPoints)
            if (freeThrowPoints < 0 && freeThrowPoints > 3) {
                onView(withId(R.id.TeamBScore)).check(matches(withText("0")))
            }
        }
    }

    private fun getNumFromTV(flag: Int) {
        var scoreID = 0
        if (flag == 1) {
            scoreID = R.id.TeamAScore
        } else if (flag == 2) {
            scoreID = R.id.TeamBScore
        }
        onView(withId(scoreID)).check { view, noViewFoundException ->
            val score = (view as TextView).text.toString()
            numFromTV = Integer.parseInt(score)
        }
    }

}