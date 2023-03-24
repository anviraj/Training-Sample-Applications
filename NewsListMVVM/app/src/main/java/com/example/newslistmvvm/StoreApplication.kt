package com.example.newslistmvvm

import android.app.Application
import com.example.newslistmvvm.api.NewsAPI
import com.example.newslistmvvm.repository.NewsRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class StoreApplication : Application() {

    lateinit var newsAPI: NewsAPI
    lateinit var newsRepository: NewsRepository

    override fun onCreate() {
        super.onCreate()

        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://newsapi.org/v2/")
            .build()

        newsAPI = retrofit.create(NewsAPI::class.java)
        newsRepository = NewsRepository(newsAPI)
    }
}
