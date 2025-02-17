package com.example.teledoctor.db.calendar

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "calendar_slot_table")
data class CalendarSlot(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    var date: String,
    var timeSlot: String,
    var from: String,
    var to: String
): Serializable
