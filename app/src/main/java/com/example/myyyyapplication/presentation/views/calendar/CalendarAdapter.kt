package com.example.myyyyapplication.presentation.views.calendar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myyyyapplication.R

class CalendarAdapter(
    private val daysList: List<String>,
    private var selectedPosition: Int = -1,
    private val onDateSelected: (Int, String) -> Unit
) : RecyclerView.Adapter<CalendarAdapter.DayViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_day, parent, false)
        return DayViewHolder(view)
    }

    override fun onBindViewHolder(holder: DayViewHolder, position: Int) {
        val day = daysList[position]
        holder.dayText.text = day

        if (day.isNotEmpty()) {
            if (position == selectedPosition) {
                holder.dayText.setBackgroundResource(R.drawable.bg_selected_day)
            } else {
                holder.dayText.setBackgroundResource(R.drawable.bg_day)
            }
        }

        holder.itemView.setOnClickListener {
            if (day.isNotEmpty()) {
                val previousPosition = selectedPosition
                selectedPosition = position
                notifyItemChanged(previousPosition)
                notifyItemChanged(position)
                onDateSelected(position, day)
            }
        }
    }

    override fun getItemCount(): Int = daysList.size

    class DayViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val dayText: TextView = itemView.findViewById(R.id.dayText)
    }
}
