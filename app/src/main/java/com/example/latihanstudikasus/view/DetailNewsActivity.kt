package com.example.latihanstudikasus.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.latihanstudikasus.R
import com.example.latihanstudikasus.model.NewsResponseItem
import kotlinx.android.synthetic.main.activity_detail_news.*

class DetailNewsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_news)

        val detailNews = intent.getParcelableExtra<NewsResponseItem>("DETAIL_NEWS")

        tv_detail_title_news.text = detailNews?.title
        tv_detail_date_news.text = detailNews?.createdAt
        tv_detail_author_news.text = detailNews?.author
        tv_detail_description_news.text = detailNews?.description
        Glide.with(this).load(detailNews?.image).into(iv_detail_image_news)

    }
}