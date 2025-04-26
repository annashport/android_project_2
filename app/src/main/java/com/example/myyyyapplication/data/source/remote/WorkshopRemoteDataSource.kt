package com.example.myyyyapplication.data.source.remote

import com.example.myyyyapplication.data.source.remote.model.WorkshopModel

interface WorkshopRemoteDataSource {

    suspend fun loadWorkshops(): List<WorkshopModel>
}