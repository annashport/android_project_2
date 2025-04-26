package com.example.myyyyapplication.data.source.local
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface WorkshopDao {

    @Insert
    suspend fun insert(workshopEntity: WorkshopEntity)

    @Insert
    suspend fun insertAll(workshopEntityList: List<WorkshopEntity>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(workshopEntity: WorkshopEntity)

    @Query("SELECT (SELECT COUNT(*) FROM my_table) == 0")
    fun isEmpty(): Boolean

    @Query("SELECT * FROM my_table WHERE isLiked = 1")
    suspend fun getLiked(): List<WorkshopEntity>

    @Query("SELECT * FROM my_table")
    suspend fun getAll(): List<WorkshopEntity>


    @Query("SELECT * FROM my_table WHERE interests = :clubInterests")
    suspend fun getClubByInterests(clubInterests: String): WorkshopEntity?

}