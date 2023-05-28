package com.example.zennexapp.data.datasource.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.zennexapp.data.datasource.local.model.NewsDbModel

@Dao
interface NewsDao {

	@Query("SELECT * FROM news")
	suspend fun getNewsDb(): List<NewsDbModel>

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun saveNewsDb(news: List<NewsDbModel>)

}