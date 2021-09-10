package com.example.kotlinoneactivityapp


import android.util.Log
import android.widget.TextView

import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

import android.support.test.runner.AndroidJUnit4
import android.support.test.rule.ActivityTestRule
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.matcher.ViewMatchers.withId

@RunWith(AndroidJUnit4::class)

class MainActivityTest {

    @get:Rule
    var mActivityTestRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    private val entityProcessor = EntityProcessor()
    private val entityInteractor = EntityInteractor()

    private var numFromTV       = 0
    private var numAfterClick   = 0
    private var freeThrowPoints = 0
    private var numBeforeClick  = 0

    private fun checkDefaultState() {
        entityProcessor.checkSymbolsArePresent("Team A")
        entityProcessor.checkSymbolsArePresent("Team B")
        entityProcessor.checkSymbolsOnElement(R.id.TeamAScore, "0")
        entityProcessor.checkSymbolsOnElement(R.id.TeamBScore, "0")
        entityProcessor.checkSymbolsOnElement(R.id.plus3Abutton, "+3 POINTS")
        entityProcessor.checkSymbolsOnElement(R.id.plus3Bbutton, "+3 POINTS")
        entityProcessor.checkSymbolsOnElement(R.id.plus2Abutton, "+2 POINTS")
        entityProcessor.checkSymbolsOnElement(R.id.plus2Bbutton, "+2 POINTS")
        entityProcessor.checkSymbolsOnElement(R.id.freeAthrow, "FREE THROW")
        entityProcessor.checkSymbolsOnElement(R.id.freeBthrow, "FREE THROW")
        entityProcessor.checkSymbolsArePresent("RESET")
    }

    @Test
    fun plusThreeTest() {
        checkDefaultState()
        entityInteractor.clickOnButtonWithID(R.id.plus3Abutton)
        entityProcessor.checkSymbolsOnElement(R.id.TeamAScore, "3")
        entityInteractor.clickOnButtonWithID(R.id.plus3Bbutton)
        entityProcessor.checkSymbolsOnElement(R.id.TeamBScore, "3")
    }

    @Test
    fun plusTwoTest() {
        checkDefaultState()
        entityInteractor.clickOnButtonWithID(R.id.plus2Abutton)
        entityProcessor.checkSymbolsOnElement(R.id.TeamAScore, "2")
        entityInteractor.clickOnButtonWithID(R.id.plus2Bbutton)
        entityProcessor.checkSymbolsOnElement(R.id.TeamBScore, "2")
    }

    @Test
    fun resetScoreTest() {
        checkDefaultState()
        entityInteractor.clickOnButtonWithID(R.id.plus2Bbutton)
        entityProcessor.checkSymbolsOnElement(R.id.TeamBScore, "2")
        entityInteractor.clickOnButtonWithID(R.id.plus3Abutton)
        entityProcessor.checkSymbolsOnElement(R.id.TeamAScore, "3")
        entityInteractor.clickOnButtonWithID(R.id.resetButton)
        entityProcessor.checkSymbolsOnElement(R.id.TeamAScore, "0")
        entityProcessor.checkSymbolsOnElement(R.id.TeamBScore, "0")
    }

    private fun getNumFromTV(flag: Int) {
        //1 is for Team A and 2 for Team B
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

    private fun checkRandomizerBounds() {
        Log.d("MyApp", "" + freeThrowPoints)
        if (freeThrowPoints !in 0..3) {
            entityProcessor.checkSymbolsOnElement(R.id.TeamAScore, "0")
        }
    }

    @Test
    fun checkFreeAThrow() {
        checkDefaultState()
        for (i in 0..30) {
            getNumFromTV(1)
            numBeforeClick = numFromTV
            entityInteractor.clickOnButtonWithID(R.id.freeAthrow)
            getNumFromTV(1)
            numAfterClick = numFromTV
            freeThrowPoints = numAfterClick - numBeforeClick
            checkRandomizerBounds()
        }
    }

    @Test
    fun checkFreeBThrow() {
        for (i in 0..30) {
            getNumFromTV(2)
            numBeforeClick = numFromTV
            entityInteractor.clickOnButtonWithID(R.id.freeBthrow)
            getNumFromTV(2)
            numAfterClick = numFromTV
            freeThrowPoints = numAfterClick - numBeforeClick
            checkRandomizerBounds()
        }
    }

}