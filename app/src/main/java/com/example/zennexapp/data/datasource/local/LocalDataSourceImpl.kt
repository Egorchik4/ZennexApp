package com.example.zennexapp.data.datasource.local

import com.example.zennexapp.data.datasource.local.model.NewsDbModel
import com.example.zennexapp.data.datasource.local.room.NewsDao
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
	private val newsDao: NewsDao
) : LocalDataSource {

	override suspend fun saveData(news: List<NewsDbModel>) {
		newsDao.saveNewsDb(news)
	}

	override suspend fun getData(): List<NewsDbModel> =
		newsDao.getNewsDb()
}