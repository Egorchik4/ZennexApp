package com.example.zennexapp.data.model

import com.google.gson.annotations.SerializedName

data class NewsModel(
	@SerializedName("status")
	val status: String?,
	@SerializedName("totalResults")
	val totalResults: Int?,
	@SerializedName("articles")
	val articles: List<ArticlesModel>
)

data class ArticlesModel(
	@SerializedName("source")
	val source: SourceModel?,
	@SerializedName("author")
	val author: String?,
	@SerializedName("title")
	val title: String?,
	@SerializedName("description")
	val description: String?,
	@SerializedName("url")
	val url: String?,
	@SerializedName("urlToImage")
	val urlToImage: String?,
	@SerializedName("publishedAt")
	val publishedAt: String?,
	@SerializedName("content")
	val content: String?
)

data class SourceModel(
	@SerializedName("id")
	val id: String?,
	@SerializedName("name")
	val name: String?
)