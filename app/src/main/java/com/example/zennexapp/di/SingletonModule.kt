package com.example.zennexapp.di

import android.content.Context
import androidx.room.Room
import com.example.zennexapp.data.datasource.local.LocalDataSource
import com.example.zennexapp.data.datasource.local.LocalDataSourceImpl
import com.example.zennexapp.data.datasource.local.room.LocalDatabase
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
import dagger.hilt.android.qualifiers.ApplicationContext
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
	fun providePagingRepository(networkDataSource: NetworkDataSource, localDataSource: LocalDataSource): AppRepository {
		return AppRepositoryImpl(networkDataSource, localDataSource)
	}

	@Provides
	@Singleton
	fun provideRoomFactory(@ApplicationContext context: Context): LocalDatabase {
		return Room.databaseBuilder(context, LocalDatabase::class.java, "news_room_database.db")
			.build()
	}

	@Provides
	@Singleton
	fun provideLocalDataSource(localDb: LocalDatabase): LocalDataSource {
		return LocalDataSourceImpl(localDb.getNewsDao())
	}

	@Provides
	@Singleton
	fun provideGetNewsFlowUseCase(repository: AppRepository): GetNewsUseCase {
		return GetNewsUseCase(repository)
	}
}