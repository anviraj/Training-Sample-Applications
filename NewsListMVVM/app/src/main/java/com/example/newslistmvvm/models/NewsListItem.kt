package com.example.newslistmvvm.models

data class NewsListItem(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)