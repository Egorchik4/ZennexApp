package com.example.zennexapp.data.datasource.local.room

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.zennexapp.data.datasource.local.model.NewsDbModel

@Dao
interface NewsDao {

	@Query("SELECT * FROM news")
	fun getNewsDb(): PagingSource<Int, NewsDbModel>

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun saveNewsDb(news: List<NewsDbModel>)

}