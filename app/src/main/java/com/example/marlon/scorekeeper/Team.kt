package com.example.marlon.scorekeeper

import android.os.Parcel
import android.os.Parcelable

data class Team(var goals: Int = 0,
           private var penaltyGoals: Int = 0,
           var fouls: Int = 0,
           private var yellowCards: Int = 0,
           private var redCardsByFouls: Int = 0,
           private var redCardsByYellowCards: Int = 0) : Parcelable {

    fun addGoal()=goals++
    fun addPenaltyGoal()=penaltyGoals++
    fun addFoul()=fouls++
    fun addYellowCard()=yellowCards++
    fun addRedCard()= redCardsByFouls++
    fun addRedCardsByYellowCards()=redCardsByYellowCards++
    fun hasPairYellowCards(): Boolean {
        return yellowCards.rem(2)==0
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

