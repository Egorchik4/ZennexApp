package com.example.zennexapp.domain.repository

import com.example.zennexapp.domain.entity.NewsListEntity

interface Repository {

	suspend fun getNewsFromPage(page: Int): NewsListEntity
}