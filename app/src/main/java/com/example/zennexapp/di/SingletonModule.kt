package com.example.zennexapp.di

import com.example.zennexapp.data.datasource.network.NetworkDataSource
import com.example.zennexapp.data.datasource.network.NetworkDataSourceImpl
import com.example.zennexapp.data.datasource.network.api.Api
import com.example.zennexapp.data.datasource.network.retrofit.RetrofitFactory
import com.example.zennexapp.data.repository.AppRepositoryImpl
import com.example.zennexapp.domain.repository.AppRepository
import com.example.zennexapp.domain.usecase.GetNewsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SingletonModule {

	@Provides
	@Singleton
	fun provideRetrofitApiFactory(): Api {
		return RetrofitFactory().api
	}

	@Provides
	@Singleton
	fun provideNetworkDataSource(api: Api): NetworkDataSource {
		return NetworkDataSourceImpl(api)
	}

	@Provides
	@Singleton
	fun providePagingRepository(networkDataSource: NetworkDataSource): AppRepository {
		return AppRepositoryImpl(networkDataSource)
	}

	@Provides
	@Singleton
	fun provideGetNewsFlowUseCase(repository: AppRepository): GetNewsUseCase {
		return GetNewsUseCase(repository)
	}
}