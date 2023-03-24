package com.example.newslistmvvm.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newslistmvvm.DetailActivity
import com.example.newslistmvvm.R
import com.example.newslistmvvm.models.Article
import kotlinx.android.synthetic.main.recycler_list_row.view.*

class NewsAdapter(private val newsList: List<Article>): RecyclerView.Adapter<NewsAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsAdapter.MyViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.recycler_list_row, parent, false )
        return MyViewHolder(inflater)

    }

    override fun onBindViewHolder(holder: NewsAdapter.MyViewHolder, position: Int) {
        newsList.get(position).apply {
            holder.bind(this)
            holder.recyclerView.setOnClickListener {
                val intent = Intent(holder.recyclerView.context, DetailActivity::class.java)
                intent.putExtra("title", title)
                intent.putExtra("image", urlToImage)
                intent.putExtra("description", "Description: \n$description")
                intent.putExtra("author", "Author: $author")
                intent.putExtra("publishat", "PublishedAt: $publishedAt")
                holder.recyclerView.context.startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    class   MyViewHolder(view: View): RecyclerView.ViewHolder(view) {

        val tvTitle = view.tvTitle
        val tvDescription = view.tvDescription
        val thumbImageView = view.thumbImageView
        val recyclerView =view.recyclerView

        fun bind(data : Article){
            tvTitle.text = data.title
            tvDescription.text = data.description

            val url  = data.urlToImage
            Glide.with(thumbImageView)
                .load(url)
                .circleCrop()
                .into(thumbImageView)
        }
    }
}