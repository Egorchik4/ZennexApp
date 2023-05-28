package com.example.zennexapp.data.datasource.local.mapper

import com.example.zennexapp.data.datasource.local.model.NewsDbModel
import com.example.zennexapp.domain.entity.ArticleEntity

fun NewsDbModel.toArticleEntity(): ArticleEntity =
	ArticleEntity(
		title = title,
		description = description,
		url = url,
		urlToImage = urlToImage,
		publishedAt = publishedAt
	)

fun List<ArticleEntity>.toListNewsDbEntity(): List<NewsDbModel> = map(ArticleEntity::toNewsDbEntity)

fun ArticleEntity.toNewsDbEntity(): NewsDbModel =
	NewsDbModel(
		id = 0,
		title = title ?: "",
		description = description ?: "",
		url = url ?: "",
		urlToImage = urlToImage ?: "",
		publishedAt = publishedAt ?: ""
	)