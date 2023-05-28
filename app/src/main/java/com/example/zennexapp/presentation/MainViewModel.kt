package com.example.zennexapp.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.zennexapp.domain.entity.ArticleEntity
import com.example.zennexapp.domain.usecase.GetNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
	private val getNewsFlowUseCase: GetNewsUseCase
) : ViewModel() {

	private val _state = MutableLiveData("")
	val usersFlow: Flow<PagingData<ArticleEntity>> =
		_state
			.asFlow()
			.flatMapLatest { getNewsFlowUseCase.invoke() }
			.cachedIn(viewModelScope)

}