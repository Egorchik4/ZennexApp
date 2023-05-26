package com.example.zennexapp.presentation.state

import com.example.zennexapp.domain.entity.ArticleEntity

sealed class NewsItemState {

	object Loading : NewsItemState()

	data class ArticleItem(val newsItem: ArticleEntity) : NewsItemState()

	object Error : NewsItemState()
}
