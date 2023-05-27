package com.example.zennexapp.data.datasource.local

import com.example.zennexapp.data.datasource.local.model.NewsDbModel

interface LocalDataSource {

	suspend fun saveData(news: List<NewsDbModel>)

	suspend fun getData(): List<NewsDbModel>
}