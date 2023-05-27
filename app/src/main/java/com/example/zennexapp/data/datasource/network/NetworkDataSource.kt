package com.example.zennexapp.data.datasource.network

import com.example.zennexapp.data.datasource.network.model.NewsModel

interface NetworkDataSource {

	suspend fun getNews(page: Int, pageSize: Int): NewsModel
}