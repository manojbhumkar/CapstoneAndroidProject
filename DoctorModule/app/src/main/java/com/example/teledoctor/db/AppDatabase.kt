package com.example.teledoctor.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.teledoctor.db.calendar.CalendarSlot
import com.example.teledoctor.db.calendar.CalendarSlotDao
import com.example.teledoctor.db.doctor.Doctor
import com.example.teledoctor.db.doctor.DoctorDao

@Database(entities = [Doctor::class, CalendarSlot::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun doctorDao(): DoctorDao
    abstract fun calendarSlotDao(): CalendarSlotDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
