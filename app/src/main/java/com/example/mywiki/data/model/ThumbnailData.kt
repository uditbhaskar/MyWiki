package com.example.mywiki.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ThumbnailData(
    @Expose
    @SerializedName("source")
    val source :String?
)
