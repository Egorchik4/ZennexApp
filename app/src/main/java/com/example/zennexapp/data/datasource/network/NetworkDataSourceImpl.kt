package com.example.zennexapp.data.datasource.network

import com.example.zennexapp.data.datasource.network.api.Api
import javax.inject.Inject

class NetworkDataSourceImpl @Inject constructor(private val api: Api) : NetworkDataSource {

	override suspend fun getNews(page: Int, pageSize: Int) =
		api.getNews(page, pageSize)
}