package com.example.zennexapp.data.datasource

import javax.inject.Inject

class DataSourceImpl @Inject constructor(private val retrofit: RetrofitFactory) : DataSource {

	override suspend fun getNews(page: Int) =
		retrofit.api.getNews(page)
}