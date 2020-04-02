package com.example.cdg_test.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cdg_test.db.AppDatabase.Companion.DATABASE_VERSION
import com.example.cdg_test.model.dto_model.NewsDto
import com.example.cdg_test.model.dto_model.SourcesDto

@Database(entities = [NewsDto::class, SourcesDto::class], version = DATABASE_VERSION)
abstract class AppDatabase : RoomDatabase(){

    abstract fun newsDao(): NewsDao
    abstract fun sourcesDao(): SourcesDao

    companion object{
        const val DATABASE_VERSION = 1
    }
}