package com.example.zennexapp.domain.usecase

import com.example.zennexapp.domain.repository.Repository
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(private val repository: Repository) {

	suspend operator fun invoke(page: Int) =
		repository.getNewsFromPage(page)
}