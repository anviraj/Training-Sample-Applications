package com.example.newslistmvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newslistmvvm.models.NewsListItem
import com.example.newslistmvvm.repository.NewsRepository
import com.example.newslistmvvm.utils.NetworkResult
import kotlinx.coroutines.launch

class MainViewModel(private val repository: NewsRepository): ViewModel() {

    private val _news = MutableLiveData<NetworkResult<NewsListItem>>()
    val news: LiveData<NetworkResult<NewsListItem>>
        get() = _news

    fun getNews(){
        viewModelScope.launch {
            val result = repository.getNews("techcrunch", "3d0b4a92c76f4b279081bf91e8df8c13")
            _news.postValue(result)
        }
    }
}