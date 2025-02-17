package com.example.teledoctor.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.teledoctor.adapter.Patient
import com.example.teledoctor.adapter.PatientAdapter
import com.example.teledoctor.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intiUI()
    }

    private fun intiUI() {
        // Sample data for patients
        val patientList = listOf(
            Patient("John Doe", 30, "11:00AM to 12:00PM", "14/02/2025", "Healthy"),
            Patient("Michael Johnson", 60,  "05:00PM to 05:30PM", "13/02/2025", "Diabetes"),
            Patient("Jane Smith", 45,  "09:00AM to 09:30AM", "13/02/2025", "Hypertension"),
            Patient("Emily Davis", 25,  "10:00AM to 10:30AM", "12/02/2025", "Healthy"),
            Patient("Robert Brown", 50, "09:00AM to 09:30AM", "12/02/2025",  "Asthma")
        )

        // Initialize RecyclerView
        binding.dashbordPatienRecyclerView.layoutManager = LinearLayoutManager(this)

        // Set the adapter
        val adapter = PatientAdapter(patientList)
        binding.dashbordPatienRecyclerView.adapter = adapter

        binding.profileAppCompatTextView.setOnClickListener {
            startActivity(Intent(this@MainActivity, ProfileActivity::class.java))
        }

        binding.calendarAppCompatTextView.setOnClickListener {
            startActivity(Intent(this@MainActivity, CalenderTimeSlotActivity::class.java))
        }
    }
}