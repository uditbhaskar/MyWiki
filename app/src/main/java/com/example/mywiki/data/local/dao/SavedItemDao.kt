package com.example.mywiki.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.mywiki.data.local.entity.SavedItemEntity
import com.example.mywiki.utils.common.Resource
import io.reactivex.Single

@Dao
interface SavedItemDao {
    @Query("SELECT * FROM savedItem_entity ORDER BY id DESC")
    fun getAll(): Single<List<SavedItemEntity>>

    @Insert
    fun insert(entity: SavedItemEntity)

    @Delete
    fun delete(entity:SavedItemEntity)
}