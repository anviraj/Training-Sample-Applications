package com.example.newslistmvvm.repository

import com.example.newslistmvvm.api.NewsAPI
import com.example.newslistmvvm.models.NewsListItem
import com.example.newslistmvvm.utils.NetworkResult

class NewsRepository(private val newsAPI: NewsAPI) {

    suspend fun getNews(source: String, apikey: String): NetworkResult<NewsListItem> {
        val response = newsAPI.getNews(source, apikey)
        return if (response.isSuccessful){
            val responseBody = response.body()
            if (responseBody != null){
                NetworkResult.Success(responseBody)
            }
            else {
                NetworkResult.Error("Something went wrong")
            }
        }
        else{
            NetworkResult.Error("Something went wrong")
        }
    }
}
