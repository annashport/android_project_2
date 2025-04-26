package com.example.myyyyapplication.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myyyyapplication.R
import com.example.myyyyapplication.data.source.remote.model.WorkshopModel

class LikedAdapter(
    private val workshops: MutableList<WorkshopModel>,
    private val onUnlikedClickListener: (workshop: WorkshopModel) -> Unit
): RecyclerView.Adapter<LikedAdapter.ViewHolder>() {

    class ViewHolder(item: View, private val onUnlikedClickListener: (workshop: WorkshopModel, pos: Int) -> Unit): RecyclerView.ViewHolder(item) {

        private val nameTV = item.findViewById<TextView>(R.id.clubNameTextView8)
        private val addressTV = item.findViewById<TextView>(R.id.clubAddressTextView8)
        private val phoneTV = item.findViewById<TextView>(R.id.clubPhoneTextView8)
        private val siteTV = item.findViewById<TextView>(R.id.clubWebsiteTextView8)
        private val save = item.findViewById<ImageView>(R.id.saveButton8)

        fun bind(workshop: WorkshopModel) {
            nameTV.text = workshop.name
            addressTV.text = workshop.address
            phoneTV.text = workshop.phone
            siteTV.text = workshop.website
            if (workshop.isLiked) {
                save.setImageResource(R.drawable.favorite_saved_foreground)
                save.setOnClickListener{
                    onUnlikedClickListener.invoke(workshop.copy(isLiked = false, scheduledTime = null), adapterPosition)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.info_layout, parent, false)
        return ViewHolder(view) { workshop, pos ->
            onUnlikedClickListener.invoke(workshop)
            workshops.removeAt(pos)
            notifyItemRemoved(pos)
        }
    }

    override fun getItemCount(): Int {
        return workshops.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(workshops[position])
    }
}