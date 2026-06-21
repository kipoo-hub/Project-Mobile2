package com.example.project_mobile.data.model

import com.google.gson.annotations.SerializedName

data class QuoteResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("quote")
    val quote: String,
    @SerializedName("author")
    val author: String
)