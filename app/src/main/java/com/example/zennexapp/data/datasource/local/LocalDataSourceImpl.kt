package com.example.zennexapp.data.datasource.local

import androidx.paging.PagingSource
import com.example.zennexapp.data.datasource.local.model.NewsDbModel
import com.example.zennexapp.data.datasource.local.room.NewsDao
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
	private val newsDao: NewsDao
) : LocalDataSource {

	override fun getData(): PagingSource<Int, NewsDbModel> =
		newsDao.getNewsDb()

	override suspend fun saveData(news: List<NewsDbModel>) {
		newsDao.saveNewsDb(news)
	}
}