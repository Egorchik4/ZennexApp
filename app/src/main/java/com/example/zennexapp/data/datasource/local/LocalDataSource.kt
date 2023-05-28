package com.example.zennexapp.data.datasource.local

import androidx.paging.PagingSource
import com.example.zennexapp.data.datasource.local.model.NewsDbModel

interface LocalDataSource {

	suspend fun saveData(news: List<NewsDbModel>)

	fun getData(): PagingSource<Int, NewsDbModel>
}