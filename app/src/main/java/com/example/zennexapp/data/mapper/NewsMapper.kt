package com.example.zennexapp.data.mapper

import com.example.zennexapp.data.model.ArticlesModel
import com.example.zennexapp.data.model.NewsModel
import com.example.zennexapp.domain.entity.ArticleEntity
import com.example.zennexapp.domain.entity.NewsListEntity

fun NewsModel.toEntity(): NewsListEntity =
	NewsListEntity(
		articlesEntity = articles.toArticlesEntity()
	)

fun List<ArticlesModel>.toArticlesEntity(): List<ArticleEntity> = map(ArticlesModel::toEntity)

fun ArticlesModel.toEntity(): ArticleEntity =
	ArticleEntity(
		title = title,
		description = description,
		url = url,
		urlToImage = urlToImage,
		publishedAt = publishedAt
	)