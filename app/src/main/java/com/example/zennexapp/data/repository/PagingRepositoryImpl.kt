package com.example.zennexapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.zennexapp.data.api.Api
import com.example.zennexapp.domain.NewsPagingSource
import com.example.zennexapp.domain.entity.ArticleEntity
import com.example.zennexapp.domain.repository.PagingRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PagingRepositoryImpl @Inject constructor(private val api: Api) : PagingRepository {

	override suspend fun getPagingNewsFromPage(): Flow<PagingData<ArticleEntity>> {
		return Pager(
			config = PagingConfig(
				pageSize = PAGE_SIZE,
				initialLoadSize = PAGE_SIZE,
				prefetchDistance = PAGE_SIZE / 2,
				enablePlaceholders = false
			),
			pagingSourceFactory = { NewsPagingSource(api, PAGE_SIZE) }
		).flow
	}

	private companion object {

		const val PAGE_SIZE = 5
	}
}