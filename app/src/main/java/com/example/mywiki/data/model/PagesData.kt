package com.example.mywiki.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PagesData(
    @Expose
    @SerializedName("pageid")
    val pageid: Int?,
    @Expose
    @SerializedName("title")
    val title: String?,
    @Expose
    @SerializedName("thumbnail")
    val thumbnail: ThumbnailData?,
    @Expose
    @SerializedName("terms")
    val terms: DescriptionData?
)
