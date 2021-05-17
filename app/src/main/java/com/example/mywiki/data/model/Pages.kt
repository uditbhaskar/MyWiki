package com.example.mywiki.data.model


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Pages(
    @Expose
    @SerializedName("pages")
    val pages: List<PagesData>

)
