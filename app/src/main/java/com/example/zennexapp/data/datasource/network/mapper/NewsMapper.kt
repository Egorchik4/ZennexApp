package com.example.zennexapp.data.datasource.network.mapper

import com.example.zennexapp.data.datasource.network.model.ArticlesModel
import com.example.zennexapp.data.datasource.network.model.NewsModel
import com.example.zennexapp.domain.entity.ArticleEntity
import com.example.zennexapp.domain.entity.ArticleListEntity

fun NewsModel.toEntity(): ArticleListEntity =
	ArticleListEntity(
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