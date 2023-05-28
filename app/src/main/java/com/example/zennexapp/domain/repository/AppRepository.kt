package com.example.zennexapp.domain.repository

import androidx.paging.PagingData
import com.example.zennexapp.domain.entity.ArticleEntity
import kotlinx.coroutines.flow.Flow

interface AppRepository {

	suspend fun getNewsFromNetwork(): Flow<PagingData<ArticleEntity>>

//	suspend fun getNewsFromLocal(): List<ArticleEntity>

	//suspend fun saveNewsToLocalDataBase(list: List<ArticleEntity>)
}