package com.example.kotlinoneactivityapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private var teamAScore: Int = 0
    private var teamBScore: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun add3ToTeamA(view: View)  {
        addAndShowPoints(1, 3)
    }

    fun add2ToTeamA(view: View) {
        addAndShowPoints(1, 2)
    }

    fun addFreeThrowToTeamA(view: View) {
        addAndShowPoints(1, randomizer())
    }

    fun add3ToTeamB(view: View) {
        addAndShowPoints(2, 3)
    }

    fun add2ToTeamB(view: View) {
        addAndShowPoints(2, 2)
    }

    fun addFreeThrowToTeamB(view: View) {
        addAndShowPoints(2, randomizer())
    }



    fun resetScore(view: View) {
        teamAScore = 0
        teamBScore = 0
        addAndShowPoints(1, 0)
        addAndShowPoints(2, 0)
    }

    private fun addAndShowPoints (flag: Int, value: Int) {
        var teamScore = findViewById<TextView>(R.id.TeamAScore)
        var points = 0
        if (flag == 1) {
            teamAScore += value
            points = teamAScore
            teamScore = findViewById(R.id.TeamAScore)
        } else if (flag == 2) {
            teamBScore += value
            points = teamBScore
            teamScore = findViewById(R.id.TeamBScore)
        }
        val scoreInString = "" + points
        teamScore.setText(scoreInString)
    }

    private fun randomizer() : Int {
        val randomNum: Int = Random.nextInt(4)
        return randomNum
    }

}
