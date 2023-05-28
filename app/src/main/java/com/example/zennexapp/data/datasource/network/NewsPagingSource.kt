package com.example.zennexapp.data.datasource.network

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.zennexapp.data.datasource.local.LocalDataSource
import com.example.zennexapp.data.datasource.local.mapper.toListNewsDbEntity
import com.example.zennexapp.data.datasource.network.mapper.toEntity
import com.example.zennexapp.domain.entity.ArticleEntity
import kotlinx.coroutines.delay
import javax.inject.Inject

class NewsPagingSource @Inject constructor(
	private val networkDataSource: NetworkDataSource,
	private val localDataSource: LocalDataSource
) : PagingSource<Int, ArticleEntity>() {

	override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArticleEntity> {
		val pageNumber = params.key ?: 1
		val pageSize = params.loadSize

		return try {
			delay(2000)
			val newsList = networkDataSource.getNews(page = pageNumber, pageSize = pageSize).toEntity().articlesEntity
			localDataSource.saveData(newsList.toListNewsDbEntity())

			return LoadResult.Page(
				data = newsList,
				prevKey = if (pageNumber == 1) null else pageNumber - 1,
				nextKey = if (newsList.size == params.loadSize) pageNumber + (params.loadSize / pageSize) else null
			)
		} catch (e: Exception) {
			LoadResult.Error(e)
		}
	}

	override fun getRefreshKey(state: PagingState<Int, ArticleEntity>): Int? {
		val lustPosition = state.anchorPosition ?: return null
		val page = state.closestPageToPosition(lustPosition) ?: return null
		return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
	}
}