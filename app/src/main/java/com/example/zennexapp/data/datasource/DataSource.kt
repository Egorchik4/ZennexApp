package com.example.zennexapp.data.datasource

import com.example.zennexapp.data.model.NewsModel

interface DataSource {

	suspend fun getNews(page: Int): NewsModel
}