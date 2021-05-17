package com.example.mywiki.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mywiki.data.local.dao.SavedItemDao
import com.example.mywiki.data.local.entity.SavedItemEntity
import javax.inject.Singleton

@Singleton
@Database(
    entities = [
        SavedItemEntity::class
    ],
    exportSchema = false,
    version = 1
)
abstract class DatabaseService : RoomDatabase() {

    abstract fun savedItemDao(): SavedItemDao

}