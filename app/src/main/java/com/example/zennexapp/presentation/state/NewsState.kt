package com.example.zennexapp.presentation.state

sealed class NewsState {

	object Loading : NewsState()

	data class NewsListEntity(val newsItemStateList: List<NewsItemState>) : NewsState()

	object Error : NewsState()
}