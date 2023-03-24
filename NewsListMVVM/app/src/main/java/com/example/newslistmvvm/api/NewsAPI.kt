package com.example.newslistmvvm.api

import com.example.newslistmvvm.models.NewsListItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {

    @GET("top-headlines")
    suspend fun getNews(@Query("sources") source: String, @Query("apiKey") apikey: String): Response<NewsListItem>

}