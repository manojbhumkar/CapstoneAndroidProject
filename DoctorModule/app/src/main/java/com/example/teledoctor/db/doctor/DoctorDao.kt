package com.example.teledoctor.db.doctor

import androidx.room.*

@Dao
interface DoctorDao {
    @Insert
    suspend fun insert(doctor: Doctor)

    @Update
    suspend fun updateUser(doctor: Doctor)

    @Query("SELECT * FROM doctor_table WHERE email = :username AND password = :password LIMIT 1")
    suspend fun getUser(username: String, password: String): Doctor?


    @Query("SELECT * FROM doctor_table WHERE email = :username LIMIT 1")
    suspend fun getDoctor(username: String): Doctor?
}
