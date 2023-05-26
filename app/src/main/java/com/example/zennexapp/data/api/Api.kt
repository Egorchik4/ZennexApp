package com.example.zennexapp.data.api

import com.example.zennexapp.data.model.NewsModel
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

	@GET("everything?q=ios&from=2019-04-00&sortBy=publishedAt&apiKey=26eddb253e7840f988aec61f2ece2907")
	suspend fun getNews(@Query("page") pageNumber: Int): NewsModel
}