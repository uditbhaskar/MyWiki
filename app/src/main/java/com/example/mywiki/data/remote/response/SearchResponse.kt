package com.example.mywiki.data.remote.response

import com.example.mywiki.data.model.Continues
import com.example.mywiki.data.model.Pages

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @Expose
    @SerializedName("batchcomplete")
    var batchcomplete: Boolean?,
    @Expose
    @SerializedName("continue")
    var total: Continues?,
    @Expose
    @SerializedName("query")
    var query : Pages?
)
