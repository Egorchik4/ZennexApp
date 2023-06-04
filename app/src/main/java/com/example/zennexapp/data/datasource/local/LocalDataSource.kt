package com.example.zennexapp.data.datasource.local

import androidx.paging.PagingSource
import com.example.zennexapp.data.datasource.local.model.NewsDbModel

interface LocalDataSource {

	fun getData(): PagingSource<Int, NewsDbModel>

	suspend fun saveData(news: List<NewsDbModel>)
}