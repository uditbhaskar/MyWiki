package com.example.mywiki.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "savedItem_entity")
data class SavedItemEntity(

    @PrimaryKey(autoGenerate = true)
    @NotNull
    val id: Long?,

    @ColumnInfo(name = "title")
    @NotNull
    val title: String?,

    @ColumnInfo(name = "description")
    @NotNull
    val description: String?,

    @ColumnInfo(name = "pageId")
    @NotNull
    val pageId: String?
)

