package com.example.teledoctor.activities

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.teledoctor.R
import com.example.teledoctor.databinding.ActivityAddCalendarTimeSlotBinding
import com.example.teledoctor.db.calendar.CalendarSlot
import com.example.teledoctor.db.calendar.CalendarSlotViewmodel
import com.example.teledoctor.db.doctor.DoctorViewModel
import java.util.Calendar

class AddCalendarTimeSlotActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddCalendarTimeSlotBinding
    private val calendar = Calendar.getInstance()
    private val calendarSlotViewmodel: CalendarSlotViewmodel by viewModels()

    private var calendarSlot: CalendarSlot? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddCalendarTimeSlotBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUI()
    }

    private fun initUI() {
        intent?.let {
            if(it.hasExtra("TIME_SLOT")) {
                calendarSlot = it.getSerializableExtra("TIME_SLOT") as CalendarSlot

                calendarSlot?.let {
                    binding.dateEditText.setText(it.date)
                    binding.fromTimeEditText.setText(it.from)
                    binding.toTimeEditText.setText(it.to)
                    binding.titleAppCompatTextView.setText("Update Calender Time Slot")
                }
            }
        }

        binding.dateEditText.setOnClickListener {
            showDatePickerDialog()
        }

        binding.fromTimeEditText.setOnClickListener {
            showTimePickerDialog(isFromTime = true)
        }

        binding.toTimeEditText.setOnClickListener {
            showTimePickerDialog(isFromTime = false)
        }

        binding.saveButton.setOnClickListener {
            saveTimeSlot()
        }
    }

    private fun showDatePickerDialog() {
        val dateSetListener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            // Set the selected date to the EditText in the format yyyy-mm-dd
            val selectedDate = "$dayOfMonth/${month + 1}/$year"
            binding.dateEditText.setText(selectedDate)
        }

        // Show DatePickerDialog
        DatePickerDialog(
            this,
            dateSetListener,
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    private fun showTimePickerDialog(isFromTime: Boolean) {
        val timeSetListener = TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
            // Format time as HH:mm
            val time = String.format("%02d:%02d", hourOfDay, minute)
            if (isFromTime) {
                binding.fromTimeEditText.setText(time)
            } else {
                binding.toTimeEditText.setText(time)
            }
        }

        // Show TimePickerDialog
        TimePickerDialog(
            this,
            timeSetListener,
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE),
            false // Use 24-hour format
        ).show()
    }

    private fun saveTimeSlot() {
        // Get the values from the EditTexts
        val date = binding.dateEditText.text.toString()
        val fromTime = binding.fromTimeEditText.text.toString()
        val toTime = binding.toTimeEditText.text.toString()

        // Perform any validation and save the time slot data
        if (date.isNotEmpty() && fromTime.isNotEmpty() && toTime.isNotEmpty()) {
            if(calendarSlot != null) {

                calendarSlot?.date = date
                calendarSlot?.from = fromTime
                calendarSlot?.to = toTime
                calendarSlot?.timeSlot = "${fromTime} - ${toTime}"
                calendarSlotViewmodel.updateCalenderSlot(calendarSlot!!) {
                    Toast.makeText(this, "Updated Calendar Time Slot!", Toast.LENGTH_SHORT).show()
                    finish()
                }
            } else {
                calendarSlotViewmodel.addCalenderSlot(CalendarSlot(date = date, timeSlot = "${fromTime} - ${toTime}", from = fromTime, to = toTime)) {
                    Toast.makeText(this, "Added Calendar Time Slot!", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
        } else {
            Toast.makeText(this, "Please fill all fields!", Toast.LENGTH_SHORT).show()
        }
    }

}