package com.example.myyyyapplication.data.repository

import com.example.myyyyapplication.data.source.local.WorkshopDao
import com.example.myyyyapplication.data.source.local.WorkshopEntity
import com.example.myyyyapplication.data.source.remote.WorkshopRemoteDataSource
import com.example.myyyyapplication.data.source.remote.model.WorkshopModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.DayOfWeek

class WorkshopRepositoryImpl(
    private val workshopRemoteDataSource: WorkshopRemoteDataSource,
    private val workshopDatabase: WorkshopDao,
): WorkshopRepository {

    override suspend fun getLikedWorkshops(): List<WorkshopModel> {
       return withContext(Dispatchers.IO) {
           checkDBandLoad()
           return@withContext workshopDatabase.getLiked()
               .map { it.toWorkshopModel() }
       }
    }


    override suspend fun getWorkshops(): List<WorkshopModel> = withContext(Dispatchers.IO){
        checkDBandLoad()
        return@withContext workshopDatabase.getAll()
            .map {
               it.toWorkshopModel()
            }
    }

    override suspend fun updateWorkshop(workshopModel: WorkshopModel) {
        withContext(Dispatchers.IO) {
            workshopDatabase.update(workshopModel.toDbEntity())
        }
    }

    private suspend fun checkDBandLoad() {
        withContext(Dispatchers.IO){
            if (workshopDatabase.isEmpty()) {
                workshopDatabase.insertAll(
                    workshopRemoteDataSource.loadWorkshops()
                        .map {
                            it.toDbEntity()
                        }
                )
            }
        }
    }

    private fun WorkshopModel.toDbEntity() =  WorkshopEntity(
        id = this.id,
        name = this.name,
        address = this.address,
        phone =  this.phone,
        website = this.website,
        interests = this.interests,
        days = this.days,
        hours = this.hours,
        price = this.price,
        longitude = this.longitude,
        latitude = this.latitude,
        isLiked = this.isLiked,
        scheduledTime = this.scheduledTime?.map { it.key.value to it.value }?.toMap(),
    )

    private fun WorkshopEntity.toWorkshopModel() =  WorkshopModel(
        id = this.id,
        name = this.name,
        address = this.address,
        phone = this.phone,
        website = this.website,
        interests = this.interests,
        days = this.days,
        hours = this.hours,
        price = this.price,
        longitude = this.longitude,
        latitude = this.latitude,
        isLiked = this.isLiked,
        scheduledTime = this.scheduledTime?.map { DayOfWeek.of(it.key) to it.value }?.toMap(),
    )
}