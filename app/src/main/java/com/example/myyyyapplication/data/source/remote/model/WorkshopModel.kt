package com.example.myyyyapplication.data.source.remote.model

import java.time.DayOfWeek

data class WorkshopModel(
    val id: Int = 0,
    val name: String,
    val address: String,
    val phone: String,
    val website: String,
    val interests: String,
    val days: String,
    val hours: String,
    val price: String,
    val longitude: Double,
    val latitude: Double,
    val isLiked: Boolean = false,
    val scheduledTime: Map<DayOfWeek, String>? = null,
) {
    fun getDaysOfWeek(): List<DayOfWeek> {
        return when(days) {
            "Пн-Нд" -> DayOfWeek.values().asList()
            "Пн-Пт" -> DayOfWeek.values().asList().subList(0, 5)
            "Сб-Нд" -> DayOfWeek.values().asList().subList(5, 7)
            else -> emptyList()
        }
    }
}
