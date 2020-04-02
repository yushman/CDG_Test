package com.example.cdg_test.di

import androidx.room.Room
import com.example.cdg_test.db.AppDatabase
import com.example.cdg_test.model.repo.FavoriteSourcesRepo
import com.example.cdg_test.model.repo.NewsRepo
import com.example.cdg_test.model.repo.SourcesRepo
import com.example.cdg_test.news_api.Api
import com.example.cdg_test.news_api.NewsApiService
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

class KoinModule {
    val koinModule = module {
        single { Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "App_database"
        ).build() }
        single { get<AppDatabase>().newsDao() }
        single { get<AppDatabase>().sourcesDao() }
        single { NewsRepo() }
        single { FavoriteSourcesRepo() }
        single { SourcesRepo() }
        single { Api() }
        single { get<Api>().createService(NewsApiService::class.java) }
        single { PersistanceScope() }
        single { NetworkingScope() }
    }
}