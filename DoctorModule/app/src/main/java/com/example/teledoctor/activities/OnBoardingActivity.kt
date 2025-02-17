package com.example.teledoctor.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.teledoctor.databinding.ActivityOnBoardingBinding
import com.example.teledoctor.db.doctor.Doctor
import com.example.teledoctor.db.doctor.DoctorViewModel

class OnBoardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnBoardingBinding
    private val doctorViewModel: DoctorViewModel by viewModels()

    private var doctor: Doctor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityOnBoardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUI()

    }

    private fun initUI() {

        intent?.let {
            if(it.hasExtra("USER_INFO")) {
                doctor = it.getSerializableExtra("USER_INFO") as Doctor

                binding.nameAppCompatEditText.setText(doctor?.username)
            }
        }
        binding.saveAppCompatButton.setOnClickListener {
            if(binding.nameAppCompatEditText.text.toString().isNullOrEmpty()) {
                Toast.makeText(this@OnBoardingActivity, "Please enter name", Toast.LENGTH_SHORT).show()
            } else if(binding.specializationAppCompatEditText.text.toString().isNullOrEmpty()) {
                Toast.makeText(this@OnBoardingActivity, "Please enter specialization", Toast.LENGTH_SHORT).show()
            } else if(binding.experienceAppCompatEditText.text.toString().isNullOrEmpty()) {
                Toast.makeText(this@OnBoardingActivity, "Please enter experience", Toast.LENGTH_SHORT).show()
            } else if(binding.degreeAppCompatEditText.text.toString().isNullOrEmpty()) {
                Toast.makeText(this@OnBoardingActivity, "Please enter degree", Toast.LENGTH_SHORT).show()
            } else if(binding.hospitalAppCompatEditText.text.toString().isNullOrEmpty()) {
                Toast.makeText(this@OnBoardingActivity, "Please enter hospital", Toast.LENGTH_SHORT).show()
            } else {
                doctor?.let {
                    it.experience = binding.experienceAppCompatEditText.text.toString()
                    it.hospital = binding.hospitalAppCompatEditText.text.toString()
                    it.degree = binding.degreeAppCompatEditText.text.toString()
                    it.specialization = binding.specializationAppCompatEditText.text.toString()
                    doctorViewModel.updateUser(it) {
                        startActivity(Intent(this@OnBoardingActivity, MainActivity::class.java))
                    }
                }
            }
        }
    }
}