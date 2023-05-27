package com.example.zennexapp.data.datasource.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
	tableName = "news"
)
data class NewsDbModel(
	@PrimaryKey(autoGenerate = true) val id: Long,
	val title: String,
	val description: String,
	val url: String,
	@ColumnInfo(name = "url_to_image") val urlToImage: String,
	@ColumnInfo(name = "published_at") val publishedAt: String
)