package com.example.marlon.scorekeeper

import android.os.Bundle
import android.os.PersistableBundle
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

        // Adds a foul to the maker, the param is 1 for the team 1 and 2 for the team 2
        val addFoulTeam1 = findViewById<Button>(R.id.add_team1_foul)
        addFoulTeam1.setOnClickListener { addFoul(1) }
        val addFoulTeam2 = findViewById<Button>(R.id.add_team2_foul)
        addFoulTeam2.setOnClickListener { addFoul(2) }

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
        // Check whether we're recreating a previously destroyed instance
        if (savedInstanceState != null) {
            with(savedInstanceState) {
                // Restore value of members from saved state
                team1 = getParcelable("keyTeam1")
                team2 = getParcelable("keyTeam2")
            }
            loadComponents()
        }
    }


    // Loads the Text box with the restored data
    private fun loadComponents() {
        val team1Goals = findViewById<TextView>(R.id.team1_goals)
        team1Goals.text = team1.goals.toString()
        val team2Goals = findViewById<TextView>(R.id.team2_goals)
        team2Goals.text = team2.goals.toString()
        val team1PenaltyGoals = findViewById<TextView>(R.id.team1_penalties)
        team1PenaltyGoals.text = team1.penaltyGoals.toString()
        val team2PenaltyGoals = findViewById<TextView>(R.id.team2_penalties)
        team2PenaltyGoals.text = team2.penaltyGoals.toString()
        val team1Fouls = findViewById<TextView>(R.id.team1_fouls)
        team1Fouls.text = team1.fouls.toString()
        val team2Fouls = findViewById<TextView>(R.id.team2_fouls)
        team2Fouls.text = team2.fouls.toString()
        val team1YellowCards = findViewById<TextView>(R.id.team1_yellow_cards)
        team1YellowCards.text = team1.yellowCards.toString()
        val team2YellowCards = findViewById<TextView>(R.id.team2_yellow_cards)
        team2YellowCards.text = team2.yellowCards.toString()
        val team1RedCards = findViewById<TextView>(R.id.team1_red_cards_fouls)
        team1RedCards.text = team1.redCardsByFouls.toString()
        val team2RedCards = findViewById<TextView>(R.id.team2_red_cards_fouls)
        team2RedCards.text = team2.redCardsByFouls.toString()
        val team1RedCardsByYellow = findViewById<TextView>(R.id.team1_red_cards_by_yellow)
        team1RedCardsByYellow.text = team1.redCardsByYellowCards.toString()
        val team2RedCardsByYellow = findViewById<TextView>(R.id.team2_red_cards_by_yellow)
        team2RedCardsByYellow.text = team2.redCardsByYellowCards.toString()
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
            if (isSecondYellowCard.isChecked && team1.validateYellowCards()) {
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
            if (isSecondYellowCard.isChecked && team2.validateYellowCards()) {
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

    // Adds a foul to the count for the team
    private fun addFoul(numberOfTeam: Int) {
        if (numberOfTeam == 1) {
            // If the the param is 1 (or another), works on the team 1
            val fouls = findViewById<TextView>(R.id.team1_fouls)
            fouls.text = team1.addFoul().toString()
        } else {
            // If the the param is 2 (or another), works on the team 2
            val fouls = findViewById<TextView>(R.id.team2_fouls)
            fouls.text = team2.addFoul().toString()
        }
    }

    // Adds a red card to the count for the team
    private fun addRedCard(numberOfTeam: Int) {
        if (numberOfTeam == 1) {
            // If the the param is 1 (or another), works on the team 1
            val redCards = findViewById<TextView>(R.id.team1_red_cards_fouls)
            redCards.text = team1.addRedCard().toString()
        } else {
            // If the the param is 2 (or another), works on the team 2
            val redCards = findViewById<TextView>(R.id.team2_red_cards_fouls)
            redCards.text = team2.addRedCard().toString()
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.run {
            putParcelable("keyTeam1", team1)
            putParcelable("keyTeam2", team2)
        }
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        savedInstanceState?.run {
            team1 = getParcelable("keyTeam1")
            team2 = getParcelable("keyTeam2")
        }
    }
}



