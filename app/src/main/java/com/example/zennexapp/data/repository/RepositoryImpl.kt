package com.example.zennexapp.data.repository

import com.example.zennexapp.data.datasource.DataSource
import com.example.zennexapp.data.mapper.toEntity
import com.example.zennexapp.domain.entity.NewsListEntity
import com.example.zennexapp.domain.repository.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val data: DataSource) : Repository {

	override suspend fun getNewsFromPage(page: Int): NewsListEntity =
		data.getNews(page).toEntity()

}