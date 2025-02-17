package com.example.teledoctor.db.calendar

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface CalendarSlotDao {
    @Insert
    suspend fun insert(calendarSlot: CalendarSlot)

    @Update
    suspend fun updateCalendarSlot(calendarSlot: CalendarSlot)

    @Delete
    suspend fun deleteCalendarSlot(calendarSlot: CalendarSlot)

    @Query("SELECT * FROM calendar_slot_table ORDER BY `from` ASC")
    fun getAllCalendarSlot(): LiveData<List<CalendarSlot>>

    @Query("SELECT * FROM calendar_slot_table WHERE id = :id LIMIT 1")
    suspend fun getCalendarSlot(id: Int): CalendarSlot?
}
