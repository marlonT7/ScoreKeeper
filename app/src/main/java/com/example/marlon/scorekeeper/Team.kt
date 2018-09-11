package com.example.marlon.scorekeeper

import android.os.Parcel
import android.os.Parcelable

data class Team(var goals: Int = 0,
                var penaltyGoals: Int = 0,
                var fouls: Int = 0,
                var yellowCards: Int = 0,
                var redCardsByFouls: Int = 0,
                var redCardsByYellowCards: Int = 0) : Parcelable {

    fun addGoal(): Int {
        goals++
        return goals
    }

    fun addPenaltyGoal(): Int {
        penaltyGoals++
        return penaltyGoals
    }

    fun addFoul():Int {
        fouls++
        return fouls
    }

    fun addYellowCard() : Int{
        yellowCards++
        return yellowCards
    }

    fun addRedCard():Int {
        redCardsByFouls++
        return redCardsByFouls
    }

    fun addRedCardsByYellowCards():Int {
        redCardsByYellowCards++
        return redCardsByYellowCards
    }

    // Validates if can add a red card
    fun validateYellowCards(): Boolean {
        return yellowCards != 0 && yellowCards / 2 > redCardsByYellowCards
    }


    constructor(source: Parcel) : this(
            source.readInt(),
            source.readInt(),
            source.readInt(),
            source.readInt(),
            source.readInt(),
            source.readInt()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt(goals)
        writeInt(penaltyGoals)
        writeInt(fouls)
        writeInt(yellowCards)
        writeInt(redCardsByFouls)
        writeInt(redCardsByYellowCards)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Team> = object : Parcelable.Creator<Team> {
            override fun createFromParcel(source: Parcel): Team = Team(source)
            override fun newArray(size: Int): Array<Team?> = arrayOfNulls(size)
        }
    }
}


