package com.example.teledoctor.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.example.teledoctor.R

class PatientAdapter(private val patientList: List<Patient>) : RecyclerView.Adapter<PatientAdapter.PatientViewHolder>() {

    // Define the ViewHolder
    class PatientViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: AppCompatTextView = itemView.findViewById(R.id.nameAppCompatTextView)
        val ageTextView: AppCompatTextView = itemView.findViewById(R.id.timeSlotAppCompatTextView)
        val conditionTextView: AppCompatTextView = itemView.findViewById(R.id.conditionAppCompatTextView)
    }

    // Called when RecyclerView needs a new ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatientViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_patient, parent, false)
        return PatientViewHolder(view)
    }

    // Called to bind the data to the ViewHolder
    override fun onBindViewHolder(holder: PatientViewHolder, position: Int) {
        val patient = patientList[position]
        holder.nameTextView.text = "${patient.name} - ${patient.age}"
        holder.ageTextView.text = "${patient.date} (${patient.timeSlot})"
        holder.conditionTextView.text = "Condition: ${patient.condition}"
    }

    // Return the total number of items in the list
    override fun getItemCount(): Int {
        return patientList.size
    }
}
