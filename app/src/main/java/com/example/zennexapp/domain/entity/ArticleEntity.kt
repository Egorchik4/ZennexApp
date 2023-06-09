package com.example.zennexapp.domain.entity

data class ArticleEntity(
	val title: String?,
	val description: String?,
	val url: String?,
	val urlToImage: String?,
	val publishedAt: String?,
)

data class ArticleListEntity(
	val articlesEntity: List<ArticleEntity>
)