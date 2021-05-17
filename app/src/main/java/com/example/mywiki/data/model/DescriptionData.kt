package com.example.mywiki.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class DescriptionData(
    @Expose
    @SerializedName("description")
    val description: List<String>?
)
