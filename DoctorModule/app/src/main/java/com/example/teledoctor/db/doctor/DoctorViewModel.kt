package com.example.teledoctor.db.doctor

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.teledoctor.db.AppDatabase
import kotlinx.coroutines.launch

class DoctorViewModel(application: Application) : AndroidViewModel(application){

    val doctorDao = AppDatabase.getDatabase(application).doctorDao()


    fun registerUser(doctor: Doctor, onRegisterResult: (String) -> Unit) {
        viewModelScope.launch {
            doctorDao.insert(doctor)
            onRegisterResult("Success")
        }
    }

    fun updateUser(doctor: Doctor, onRegisterResult: (String) -> Unit) {
        viewModelScope.launch {
            doctorDao.updateUser(doctor)
            onRegisterResult("Success")
        }
    }

    fun loginUser(username: String, password: String, onLoginResult: (Doctor?) -> Unit) {
        viewModelScope.launch {
            val user = doctorDao.getUser(username, password)
            onLoginResult(user)
        }
    }

    fun getDoctor(username: String, onLoginResult: (Doctor?) -> Unit) {
        viewModelScope.launch {
            val user = doctorDao.getDoctor(username)
            onLoginResult(user)
        }
    }
}
