package com.example.zennexapp.data.datasource.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.zennexapp.data.datasource.room.entity.NewsDbEntity

@Database(
	version = 1,
	entities = [
		NewsDbEntity::class
	]
)
abstract class AppDatabase: RoomDatabase() {

	abstract fun getNewsDao(): NewsDao

}