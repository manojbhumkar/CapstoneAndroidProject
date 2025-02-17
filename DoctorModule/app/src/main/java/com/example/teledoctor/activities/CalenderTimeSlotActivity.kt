package com.example.teledoctor.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.teledoctor.R
import com.example.teledoctor.adapter.CalendarAdapter
import com.example.teledoctor.databinding.ActivityCalenderTimeSlotBinding
import com.example.teledoctor.db.calendar.CalendarSlotViewmodel

class CalenderTimeSlotActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCalenderTimeSlotBinding
    private val calendarSlotViewmodel: CalendarSlotViewmodel by viewModels()

    private lateinit var calendarAdapter: CalendarAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalenderTimeSlotBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUI()
    }

    private fun initUI() {

        calendarAdapter = CalendarAdapter(listOf(), onEditClickListener = {
            var intent = Intent(this@CalenderTimeSlotActivity, AddCalendarTimeSlotActivity::class.java)
            intent.putExtra("TIME_SLOT", it)

            startActivity(intent)
        }, onDeleteClickListener = {
            calendarSlotViewmodel.deleteCalenderSlot(it) {
                Toast.makeText(this@CalenderTimeSlotActivity, "Time slot deleted successfully!!", Toast.LENGTH_SHORT).show()
            }
        })

        var llm = LinearLayoutManager(this@CalenderTimeSlotActivity)
        llm.orientation = LinearLayoutManager.VERTICAL

        binding.calendarRecyclerView.apply {
            adapter = calendarAdapter
            layoutManager = llm
        }

        calendarSlotViewmodel.listOfCalendarSlot.observe(this, Observer {
            println("Item: " + it)

            calendarAdapter.updateItems(it)

        })


        binding.addFloatingActionButton.setOnClickListener {
            startActivity(Intent(this@CalenderTimeSlotActivity, AddCalendarTimeSlotActivity::class.java))
        }
    }
}