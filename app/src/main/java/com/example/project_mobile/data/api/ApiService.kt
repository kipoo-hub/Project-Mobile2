package com.example.project_mobile.data.api

import com.example.project_mobile.data.model.QuoteResponse
import com.example.project_mobile.data.model.NewsResponse
import com.example.project_mobile.data.model.PhotoResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("quotes/random")
    fun getRandomQuote(): Call<QuoteResponse>

    @GET("https://berita-indo-api-next.vercel.app/api/antara-news/terkini")
    fun getLatestNews(): Call<NewsResponse>

    @GET("https://picsum.photos/v2/list")
    fun getPhotos(): Call<List<PhotoResponse>>
}