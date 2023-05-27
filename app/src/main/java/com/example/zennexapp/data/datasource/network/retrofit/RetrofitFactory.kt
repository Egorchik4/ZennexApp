package com.example.zennexapp.data.datasource.network.retrofit

import com.example.zennexapp.data.datasource.network.api.Api
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class RetrofitFactory @Inject constructor() {

	private val url: String = "https://newsapi.org/v2/"
	private val retrofit: Retrofit = Retrofit.Builder()
		.addConverterFactory(GsonConverterFactory.create())
		.baseUrl(url)
		.build()

	var api: Api = retrofit.create(Api::class.java)
}