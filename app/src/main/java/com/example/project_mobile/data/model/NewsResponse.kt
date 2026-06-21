package com.example.project_mobile.data.model

import com.google.gson.annotations.SerializedName

data class NewsResponse(
    @SerializedName("success") val success: Boolean = false,
    @SerializedName("message") val message: String = "",
    @SerializedName("data") val data: List<NewsPost> = emptyList() // Jika data kosong, jadikan list kosong, bukan null
)

data class NewsPost(
    @SerializedName("title") val title: String = "",
    @SerializedName("link") val link: String = "",
    @SerializedName("isoDate") val isoDate: String = "",
    @SerializedName("image") val image: String = "",
    @SerializedName("thumbnail") val thumbnail: String = ""
)