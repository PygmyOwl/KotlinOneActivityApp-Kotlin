package com.example.kotlinoneactivityapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    var teamAScore: Int = 0
    var teamBScore: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun add3ToTeamA(view: View)  {
        teamAScore = teamAScore + 3
        showPoints(1, teamAScore)
    }

    fun add2ToTeamA(view: View) {
        teamAScore = teamAScore + 2
        showPoints(1, teamAScore)
    }

    fun addFreeThrowToTeamA(view: View) {
        teamAScore = teamAScore + randomizer()
        showPoints(1, teamAScore)
    }

    fun add3ToTeamB(view: View) {
        teamBScore = teamBScore + 3
        showPoints(2, teamBScore)
    }

    fun add2ToTeamB(view: View) {
        teamBScore = teamBScore + 2
        showPoints(2, teamBScore)
    }

    fun addFreeThrowToTeamB(view: View) {
        teamBScore = teamBScore + randomizer()
        showPoints(2, teamBScore)
    }

    fun resetScore(view: View) {
        teamAScore = 0
        showPoints(1, teamAScore)
        teamBScore = 0
        showPoints(2, teamBScore)
    }

    protected fun showPoints (flag: Int, points: Int) {
        var teamScore = findViewById<TextView>(R.id.TeamAScore)
        if (flag == 1) {
            teamScore = findViewById(R.id.TeamAScore)
        } else if (flag == 2) {
            teamScore = findViewById(R.id.TeamBScore)
        }
        val scoreInString = "" + points
        teamScore.setText(scoreInString)
    }

    protected fun randomizer() : Int {
        val randomNum: Int = Random.nextInt(4)
        return randomNum
    }

}
