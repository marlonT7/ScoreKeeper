package com.example.marlon.scorekeeper

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.ImageButton
import android.widget.Switch
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    var team1 = Team()
    var team2 = Team()
    override fun onCreate(savedInstanceState: Bundle?) {

        setContentView(R.layout.activity_main)
        // Adds a goal to the marker, the param is 1 for the team 1 and 2 for the team 2
        val team1Goal = findViewById<Button>(R.id.button6)
        team1Goal.setOnClickListener { goal(1) }
        val team2Goal = findViewById<Button>(R.id.button7)
        team2Goal.setOnClickListener { goal(2) }

        // Adds a yellow card to the maker, the param is 1 for the team 1 and 2 for the team 2
        val addYellowCardTeam1 = findViewById<ImageButton>(R.id.add_yellow_card_team1)
        addYellowCardTeam1.setOnClickListener { addYellowCard(1) }
        val addYellowCardTeam2 = findViewById<ImageButton>(R.id.add_yellow_card_team2)
        addYellowCardTeam2.setOnClickListener { addYellowCard(2) }

        // Adds a red card to the marker, the param is 1 for the team 1 and 2 for the team 2
        val addRedCardTeam1 = findViewById<ImageButton>(R.id.add_red_card_team1)
        addRedCardTeam1.setOnClickListener { addRedCard(1) }
        val addRedCardTeam2 = findViewById<ImageButton>(R.id.add_red_card_team2)
        addRedCardTeam2.setOnClickListener { addRedCard(2) }
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) {
        }
    }

    // Adds a yellow card to the count for the team
    private fun addYellowCard(numberOfTeam: Int) {
        // If the the param is 1, works on the team 1
        if (numberOfTeam == 1) {
            val yellowCards = findViewById<TextView>(R.id.team1_yellow_cards)
            val isSecondYellowCard = findViewById<Switch>(R.id.switch1)
            // Adds the yellow card to the count
            yellowCards.text = team1.addYellowCard().toString()
            // If is the second yellow card for a player (the switch is checked), adds a red card to the count
            if (isSecondYellowCard.isChecked && team1.hasPairYellowCards()) {
                val redCard = findViewById<TextView>(R.id.team1_red_cards_by_yellow)
                redCard.text = team1.addRedCardsByYellowCards().toString()
            }
        } else {
            // If the the param is 2 (or another), works on the team 2
            val yellowCards = findViewById<TextView>(R.id.team2_yellow_cards)
            val isSecondYellowCard = findViewById<Switch>(R.id.switch2)
            // Adds the yellow card to the count
            yellowCards.text = team2.addYellowCard().toString()
            // If is the second yellow card for a player (the switch is checked), adds a red card to the count
            if (isSecondYellowCard.isChecked) {
                val redCard = findViewById<TextView>(R.id.team2_red_cards_by_yellow)
                redCard.text = team2.addRedCardsByYellowCards().toString()
            }
        }
    }

    // Adds a goal to the count for the team
    fun goal(numberOfTeam: Int) {
        if (numberOfTeam == 1) {
            // If the the param is 1 (or another), works on the team 1
            val goals = findViewById<TextView>(R.id.team1_goals)
            val penalties = findViewById<TextView>(R.id.team1_penalties)
            val isPenalty = findViewById<Switch>(R.id.is_penalty)
            // Adds a goal to the count
            goals.text = team1.addGoal().toString()
            // If is goal from a penalty (the switch is checked), adds a a penalty goal to the count
            if (isPenalty.isChecked) {
                penalties.text = team1.addPenaltyGoal().toString()
            }
        } else {
            // If the the param is 2 (or another), works on the team 2
            val goals = findViewById<TextView>(R.id.team2_goals)
            val penalties = findViewById<TextView>(R.id.team2_penalties)
            val isPenalty = findViewById<Switch>(R.id.is_penalty)
            goals.text = team2.addGoal().toString()
            // If is goal from a penalty (the switch is checked), adds a a penalty goal to the count
            if (isPenalty.isChecked) {
                penalties.text = team2.addPenaltyGoal().toString()
            }
        }
    }

    private fun addRedCard(numberOfTeam: Int) {
        if (numberOfTeam == 1) {
            val redCards = findViewById<TextView>(R.id.team1_red_cards_fouls)
            redCards.text = team1.addRedCard().toString()
        } else {
            val redCards = findViewById<TextView>(R.id.team2_red_cards_fouls)
            redCards.text = team2.addRedCard().toString()
        }
    }

}



