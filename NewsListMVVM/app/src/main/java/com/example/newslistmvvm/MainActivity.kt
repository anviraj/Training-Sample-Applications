package com.example.newslistmvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newslistmvvm.adapter.NewsAdapter
import com.example.newslistmvvm.utils.NetworkResult
import com.example.newslistmvvm.viewmodel.MainViewModel
import com.example.newslistmvvm.viewmodel.MainViewModelFactory

class MainActivity : AppCompatActivity() {
    lateinit var mainViewModel: MainViewModel
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)

        val repository = (application as StoreApplication).newsRepository
        mainViewModel = ViewModelProvider(this, MainViewModelFactory(repository))
            .get(MainViewModel::class.java)

        mainViewModel.getNews()

        mainViewModel.news.observe(this, Observer {
            when(it){
                is NetworkResult.Success -> {
                    adapter = it.data?.let { it1 -> NewsAdapter(it1.articles) }!!
                    recyclerView.adapter = adapter
                }
                is NetworkResult.Error -> {}
                else -> {}
            }
        })
    }
}