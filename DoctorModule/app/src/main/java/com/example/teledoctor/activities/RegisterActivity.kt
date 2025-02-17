package com.example.teledoctor.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.teledoctor.databinding.ActivityRegisterBinding
import com.example.teledoctor.db.doctor.Doctor
import com.example.teledoctor.db.doctor.DoctorViewModel

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private val doctorViewModel: DoctorViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUI()
    }

    private fun initUI() {
        binding.registerAppCompatButton.setOnClickListener {
            if(binding.nameAppCompatEditText.text.toString().isNullOrEmpty()) {
                Toast.makeText(this@RegisterActivity, "Please enter name", Toast.LENGTH_SHORT).show()
            } else if(binding.emailAppCompatEditText.text.toString().isNullOrEmpty()) {
                Toast.makeText(this@RegisterActivity, "Please enter email", Toast.LENGTH_SHORT).show()
            } else if(binding.passwordAppCompatEditText.text.toString().isNullOrEmpty()) {
                Toast.makeText(this@RegisterActivity, "Please enter password", Toast.LENGTH_SHORT).show()
            } else {
                var doctorInfo = Doctor(username = binding.nameAppCompatEditText.text.toString(), email = binding.emailAppCompatEditText.text.toString(), password = binding.passwordAppCompatEditText.text.toString())
                doctorViewModel.registerUser(doctorInfo) { user ->
                    var intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                    intent.putExtra("USER_INFO", doctorInfo)
                    startActivity(intent)
                    Toast.makeText(this, "Registration successfully!", Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.loginAccountAppCompatTextView.setOnClickListener {
            startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
        }
    }
}