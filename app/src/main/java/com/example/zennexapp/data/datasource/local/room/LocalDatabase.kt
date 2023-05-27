package com.example.zennexapp.data.datasource.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.zennexapp.data.datasource.local.model.NewsDbModel

@Database(
	version = 1,
	entities = [
		NewsDbModel::class
	]
)
abstract class LocalDatabase : RoomDatabase() {

	abstract fun getNewsDao(): NewsDao

}