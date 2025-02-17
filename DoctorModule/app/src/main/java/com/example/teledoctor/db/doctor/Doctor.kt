package com.example.teledoctor.db.doctor

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "doctor_table")
data class Doctor(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val username: String,
    val email: String,
    val password: String,
    var specialization: String? = null,
    var experience: String? = null,
    var degree: String? = null,
    var hospital: String? = null
): Serializable
