package com.example.teledoctor.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.teledoctor.R
import com.example.teledoctor.databinding.ActivityProfileBinding
import com.example.teledoctor.db.doctor.DoctorViewModel


class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding
    private val doctorViewModel: DoctorViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUI()
    }

    private fun initUI() {
        val sharedPreferences = getSharedPreferences(resources.getString(R.string.app_name), Context.MODE_PRIVATE)
        val savedEmail = sharedPreferences.getString("email", "")  // "default@example.com" is the default value

        if (savedEmail != null) {
            doctorViewModel.getDoctor(savedEmail) {
                it?.let { user ->
                    binding.nameAppCompatTextView.text = "Name: ${user.username}"
                    binding.emailAppCompatTextView.text = "Email: ${user.email}"
                    binding.degreeAppCompatTextView.text = "Degree: ${user.degree}"
                    binding.hospitalAppCompatTextView.text = "Hospital: ${user.hospital}"
                    binding.specializationAppCompatTextView.text = "Specialization: ${user.specialization}"
                    binding.experienceAppCompatTextView.text = "Experience: ${user.experience}"
                }
            }
        }

        binding.logoutAppCompatButton.setOnClickListener {

            val sharedPreferences = getSharedPreferences(resources.getString(R.string.app_name), Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.remove("email")
            editor.apply()

            editor.clear()
            editor.apply()

            val intent = Intent(
                this@ProfileActivity,
                LoginActivity::class.java
            )
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK) // Clear the task stack
            startActivity(intent)
            finishAffinity() // Close all activities

        }
    }
}