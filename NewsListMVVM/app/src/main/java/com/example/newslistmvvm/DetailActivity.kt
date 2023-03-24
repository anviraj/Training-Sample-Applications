package com.example.newslistmvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val title : TextView = findViewById(R.id.title_tv)
        val image: ImageView = findViewById(R.id.image_iv)
        val description: TextView = findViewById(R.id.description_tv)
        val author: TextView = findViewById(R.id.author_tv)
        val publish: TextView = findViewById(R.id.publishat_tv)

        val bundle: Bundle?= intent.extras
        val heading = bundle?.getString("title")
        val url = bundle?.getString("image")
        val detail = bundle?.getString("description")
        val authorName = bundle?.getString("author")
        val publishAt = bundle?.getString("publishat")

        title.text = heading
        description.text = detail
        author.text = authorName
        publish.text = publishAt
        Glide.with(image)
            .load(url)
            .circleCrop()
            .into(image)
    }

}