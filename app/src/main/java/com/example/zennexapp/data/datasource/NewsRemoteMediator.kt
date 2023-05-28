package com.example.zennexapp.data.datasource

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.example.zennexapp.data.datasource.local.LocalDataSource
import com.example.zennexapp.data.datasource.local.mapper.toListNewsDbEntity
import com.example.zennexapp.data.datasource.local.model.NewsDbModel
import com.example.zennexapp.data.datasource.network.NetworkDataSource
import com.example.zennexapp.data.datasource.network.mapper.toEntity
import javax.inject.Inject

@ExperimentalPagingApi
class NewsRemoteMediator @Inject constructor(
	private val networkDataSource: NetworkDataSource,
	private val localDataSource: LocalDataSource
) : RemoteMediator<Int, NewsDbModel>() {

	private var pageNumber = 1

	override suspend fun load(loadType: LoadType, state: PagingState<Int, NewsDbModel>): MediatorResult {
		pageNumber = getPageIndex(loadType) ?: return MediatorResult.Success(endOfPaginationReached = true)

		val limit = state.config.pageSize

		return try {
			val newsList = networkDataSource.getNews(page = pageNumber, pageSize = limit).toEntity().articlesEntity
			if (loadType == LoadType.APPEND) {
				localDataSource.saveData(newsList.toListNewsDbEntity())
			}
			MediatorResult.Success(
				endOfPaginationReached = newsList.size < limit
			)
		} catch (e: Exception) {
			MediatorResult.Error(e)
		}
	}

	private fun getPageIndex(loadType: LoadType): Int? {
		pageNumber = when (loadType) {
			LoadType.REFRESH -> 1
			LoadType.PREPEND -> return null
			LoadType.APPEND  -> ++pageNumber
		}
		return pageNumber
	}
}