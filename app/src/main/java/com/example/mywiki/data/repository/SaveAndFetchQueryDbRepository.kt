package com.example.mywiki.data.repository

import com.bumptech.glide.load.engine.Resource
import com.example.mywiki.data.local.DatabaseService
import com.example.mywiki.data.local.entity.SavedItemEntity
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

class SaveAndFetchQueryDbRepository @Inject constructor(
    private val databaseService: DatabaseService
) {
    fun saveItemsInDB(entity: SavedItemEntity) {
        databaseService.savedItemDao().insert(entity)
    }

    fun fetchItemsFromDB(): Single<List<SavedItemEntity>> =
        databaseService.savedItemDao().getAll()


}