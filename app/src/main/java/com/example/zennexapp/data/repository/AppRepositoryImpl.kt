package com.example.zennexapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.zennexapp.data.datasource.local.LocalDataSource
import com.example.zennexapp.data.datasource.network.NetworkDataSource
import com.example.zennexapp.data.datasource.network.NewsPagingSource
import com.example.zennexapp.domain.entity.ArticleEntity
import com.example.zennexapp.domain.repository.AppRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(
	private val networkDataSource: NetworkDataSource,
	private val localDataSource: LocalDataSource
) : AppRepository {

	override suspend fun getNewsFromNetwork(): Flow<PagingData<ArticleEntity>> {
		val pager = Pager(
			config = PagingConfig(
				pageSize = PAGE_SIZE,
				initialLoadSize = PAGE_SIZE,
				prefetchDistance = PAGE_SIZE / 2,
				enablePlaceholders = false
			),
			pagingSourceFactory = { NewsPagingSource(networkDataSource,localDataSource) }
		)
		return pager.flow
	}

	private companion object {
		const val PAGE_SIZE = 5
	}
}