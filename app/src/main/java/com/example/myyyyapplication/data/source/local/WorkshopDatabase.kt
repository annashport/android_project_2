package com.example.myyyyapplication.data.source.local
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [WorkshopEntity::class], version = 3)
@TypeConverters(WorkshopTimeConverter::class)
abstract class WorkshopDatabase : RoomDatabase() {
    abstract fun myDao(): WorkshopDao

    companion object {

        @Volatile
        private var INSTANCE: WorkshopDatabase? = null

        fun getDatabase(context: Context): WorkshopDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WorkshopDatabase::class.java,
                    "my_database"
                )
                    .addTypeConverter(WorkshopTimeConverter())
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }

    }

}