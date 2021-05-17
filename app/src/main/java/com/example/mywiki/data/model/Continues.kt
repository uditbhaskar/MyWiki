package com.example.mywiki.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Continues(
    @Expose
    @SerializedName("gpoffset")
    var gpoffset: Int
)
