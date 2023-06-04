package com.example.zennexapp.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.zennexapp.data.datasource.NewsRemoteMediator
import com.example.zennexapp.data.datasource.local.LocalDataSource
import com.example.zennexapp.data.datasource.local.mapper.toArticleEntity
import com.example.zennexapp.data.datasource.network.NetworkDataSource
import com.example.zennexapp.domain.entity.ArticleEntity
import com.example.zennexapp.domain.repository.AppRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(
	private val networkDataSource: NetworkDataSource,
	private val localDataSource: LocalDataSource
) : AppRepository {

	@ExperimentalPagingApi
	override suspend fun getNewsFromNetwork(): Flow<PagingData<ArticleEntity>> {
		val pager = Pager(
			config = PagingConfig(
				pageSize = PAGE_SIZE,
				prefetchDistance = PREFETCH_DISTANCE,
				enablePlaceholders = false
			),
			remoteMediator = NewsRemoteMediator(networkDataSource, localDataSource),
			pagingSourceFactory = { localDataSource.getData() }
		)
		return pager.flow.map { pagingData ->
			pagingData.map {
				it.toArticleEntity()
			}
		}
	}

	private companion object {
		const val PAGE_SIZE = 15
		const val PREFETCH_DISTANCE = 5
	}
}