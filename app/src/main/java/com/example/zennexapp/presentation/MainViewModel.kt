package com.example.zennexapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.zennexapp.domain.entity.ArticleEntity
import com.example.zennexapp.domain.usecase.GetNewsUseCase
import com.example.zennexapp.presentation.state.NewsItemState
import com.example.zennexapp.presentation.state.NewsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
	private val getNewsUseCase: GetNewsUseCase
) : ViewModel() {

	private val _state = MutableLiveData<NewsState>()
	val state: LiveData<NewsState> = _state

	private fun getNewsList() {
		_state.value = NewsState.Loading
		viewModelScope.launch {
			try {
				val listNews = getNewsUseCase(1)
				_state.value = NewsState.NewsListEntity(
					newsItemStateList = createNewsState(listNews.articlesEntity)
				)
			} catch (e: Exception) {
				_state.value = NewsState.Error
			}
		}

	}

	private fun createNewsState(listNews: List<ArticleEntity>): List<NewsItemState> {
		val listState = mutableListOf<NewsItemState>()
		listNews.forEach {
			listState.add(NewsItemState.ArticleItem(it))
		}
		return listState
	}

	init {
		getNewsList()
	}

}