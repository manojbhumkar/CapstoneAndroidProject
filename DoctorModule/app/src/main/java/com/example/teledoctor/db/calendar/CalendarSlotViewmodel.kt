package com.example.teledoctor.db.calendar

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.teledoctor.db.AppDatabase
import kotlinx.coroutines.launch

class CalendarSlotViewmodel(application: Application) : AndroidViewModel(application){

    val calendarSlotDao = AppDatabase.getDatabase(application).calendarSlotDao()
    val listOfCalendarSlot: LiveData<List<CalendarSlot>> = calendarSlotDao.getAllCalendarSlot()


    fun addCalenderSlot(calendarSlot: CalendarSlot, onRegisterResult: (String) -> Unit) {
        viewModelScope.launch {
            calendarSlotDao.insert(calendarSlot)
            onRegisterResult("Success")
        }
    }

    fun updateCalenderSlot(calendarSlot: CalendarSlot, onRegisterResult: (String) -> Unit) {
        viewModelScope.launch {
            calendarSlotDao.updateCalendarSlot(calendarSlot)
            onRegisterResult("Success")
        }
    }

    fun deleteCalenderSlot(calendarSlot: CalendarSlot, onLoginResult: (String) -> Unit) {
        viewModelScope.launch {
            calendarSlotDao.deleteCalendarSlot(calendarSlot)
            onLoginResult("Success")
        }
    }

    fun getCalenderSlot(cId: Int, onLoginResult: (CalendarSlot?) -> Unit) {
        viewModelScope.launch {
            val user = calendarSlotDao.getCalendarSlot(cId)
            onLoginResult(user)
        }
    }

}
