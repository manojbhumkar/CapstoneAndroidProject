package com.example.teledoctor.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.example.teledoctor.R
import com.example.teledoctor.db.calendar.CalendarSlot

class CalendarAdapter(private var calendarList: List<CalendarSlot>, private val onEditClickListener: (CalendarSlot) -> Unit, private val onDeleteClickListener: (CalendarSlot) -> Unit) : RecyclerView.Adapter<CalendarAdapter.CalendarTimeSlotViewHolder>() {

    // Define the ViewHolder
    class CalendarTimeSlotViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val dateAppCompatTextView: AppCompatTextView = itemView.findViewById(R.id.dateAppCompatTextView)
        val timeSlotAppCompatTextView: AppCompatTextView = itemView.findViewById(R.id.timeSlotAppCompatTextView)
        val editAppCompatButton: AppCompatButton = itemView.findViewById(R.id.editAppCompatButton)
        val deleteAppCompatButton: AppCompatButton = itemView.findViewById(R.id.deleteAppCompatButton)
    }

    fun updateItems(listCalendar: List<CalendarSlot>) {
        calendarList  = listCalendar
        notifyDataSetChanged()
    }

    // Called when RecyclerView needs a new ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarTimeSlotViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_calendar, parent, false)
        return CalendarTimeSlotViewHolder(view)
    }

    // Called to bind the data to the ViewHolder
    override fun onBindViewHolder(holder: CalendarTimeSlotViewHolder, position: Int) {
        val calendarSlot = calendarList[position]
        holder.dateAppCompatTextView.text = "${calendarSlot.date}"
        holder.timeSlotAppCompatTextView.text = "${calendarSlot.timeSlot}"

        holder.editAppCompatButton.setOnClickListener {
            onEditClickListener(calendarSlot)
        }

        holder.deleteAppCompatButton.setOnClickListener {
            onDeleteClickListener(calendarSlot)
        }
    }

    // Return the total number of items in the list
    override fun getItemCount(): Int {
        return calendarList.size
    }
}
