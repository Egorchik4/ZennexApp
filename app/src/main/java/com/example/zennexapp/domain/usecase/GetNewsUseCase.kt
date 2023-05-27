package com.example.zennexapp.domain.usecase

import com.example.zennexapp.domain.repository.AppRepository
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(private val repository: AppRepository) {

	suspend operator fun invoke() =
		repository.getNewsFromNetwork()
}