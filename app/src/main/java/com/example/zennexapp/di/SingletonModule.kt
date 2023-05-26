package com.example.zennexapp.di

import com.example.zennexapp.data.datasource.DataSource
import com.example.zennexapp.data.datasource.DataSourceImpl
import com.example.zennexapp.data.datasource.RetrofitFactory
import com.example.zennexapp.data.repository.RepositoryImpl
import com.example.zennexapp.domain.repository.Repository
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
	fun provideRetrofitFactory(): RetrofitFactory {
		return RetrofitFactory()
	}

	@Provides
	@Singleton
	fun provideDataSource(retrofit: RetrofitFactory): DataSource {
		return DataSourceImpl(retrofit)
	}

	@Provides
	@Singleton
	fun provideRepository(dataSource: DataSource): Repository {
		return RepositoryImpl(dataSource)
	}

	@Provides
	@Singleton
	fun provideGetNewsUseCase(repository: Repository): GetNewsUseCase {
		return GetNewsUseCase(repository)
	}
}