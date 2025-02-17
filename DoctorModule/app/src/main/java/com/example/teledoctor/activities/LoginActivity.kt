package com.example.teledoctor.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.teledoctor.R
import com.example.teledoctor.databinding.ActivityLoginBinding
import com.example.teledoctor.db.doctor.DoctorViewModel


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val doctorViewModel: DoctorViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUI()
    }

    private fun initUI() {
        val sharedPreferences = getSharedPreferences(resources.getString(R.string.app_name), Context.MODE_PRIVATE)
        val savedEmail = sharedPreferences.getString("email", null)  // "default@example.com" is the default value

        println("savedEmail:  "  + savedEmail)
        if(savedEmail != null) {
            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
            finish()
        }

        binding.loginAppCompatButton.setOnClickListener {
            if(binding.emailAppCompatEditText.text.toString().isNullOrEmpty()) {
                Toast.makeText(this@LoginActivity, "Please enter email", Toast.LENGTH_SHORT).show()
            } else if(binding.passwordAppCompatEditText.text.toString().isNullOrEmpty()) {
                Toast.makeText(this@LoginActivity, "Please enter password", Toast.LENGTH_SHORT).show()
            } else {
                doctorViewModel.loginUser(binding.emailAppCompatEditText.text.toString(), binding.passwordAppCompatEditText.text.toString()) { user ->

                    if (user != null) {
                        val sharedPreferences = getSharedPreferences(resources.getString(R.string.app_name), Context.MODE_PRIVATE)
                        val editor = sharedPreferences.edit()
                        editor.putString("email", user.email)
                        editor.apply()

                        if(user.degree == null || user.experience == null || user.hospital == null) {
                            var intent = Intent(this@LoginActivity, OnBoardingActivity::class.java)
                            intent.putExtra("USER_INFO", user)
                            startActivity(intent)
                            finish()
                        } else {
                            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                            finish()
                        }
                        Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Invalid credentials", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        binding.registerAccountAppCompatTextView.setOnClickListener {
            finish()
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
        }
    }
}