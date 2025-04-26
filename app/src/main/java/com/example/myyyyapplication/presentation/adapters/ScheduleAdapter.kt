package com.example.myyyyapplication.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myyyyapplication.R
import com.example.myyyyapplication.data.source.remote.model.WorkshopModel
import java.time.DayOfWeek

class ScheduleAdapter(
    private val workshops: MutableList<WorkshopModel>,
    private val dayOfWeek: DayOfWeek,
    private val deleteClickListener: (workshop: WorkshopModel) -> Unit,
): RecyclerView.Adapter<ScheduleAdapter.ViewHolder>() {

    class ViewHolder(item: View, private val deleteClickListener: (workshop: WorkshopModel,pos: Int) -> Unit): RecyclerView.ViewHolder(item) {
        private val time = item.findViewById<TextView>(R.id.tvTime)
        private val name = item.findViewById<TextView>(R.id.tvName)
        private val delete = item.findViewById<ImageView>(R.id.delete)

        fun bind(workshop: WorkshopModel, dayOfWeek: DayOfWeek) {
            time.text = "${workshop.scheduledTime?.get(dayOfWeek)} |"
            name.text = workshop.name
            delete.setOnClickListener {
                deleteClickListener.invoke(workshop.copy(scheduledTime = workshop.scheduledTime?.minus(dayOfWeek)), adapterPosition)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.schedule_item, parent, false)
        return ViewHolder(view) { workshop, pos ->
            deleteClickListener.invoke(workshop)
            workshops.removeAt(pos)
            notifyItemRemoved(pos)
        }
    }

    override fun getItemCount(): Int {
        return workshops.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(workshops[position], dayOfWeek)
    }
}