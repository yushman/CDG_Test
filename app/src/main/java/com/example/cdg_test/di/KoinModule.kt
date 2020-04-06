package com.example.cdg_test.di

import androidx.room.Room
import com.example.cdg_test.db.AppDatabase
import com.example.cdg_test.model.repo.FavoriteSourcesRepo
import com.example.cdg_test.model.repo.NewsRepo
import com.example.cdg_test.model.repo.SourcesRepo
import com.example.cdg_test.news_api.Api
import com.example.cdg_test.news_api.NewsApiService
import com.example.cdg_test.presentation.fragment.FavoriteSourcesViewModel
import com.example.cdg_test.presentation.fragment.NewsBySourceViewModel
import com.example.cdg_test.presentation.fragment.NewsSearchViewModel
import com.example.cdg_test.presentation.fragment.SourcesViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
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
        single { Api(androidContext()) }
        single { get<Api>().createService(NewsApiService::class.java) }
        single { PersistanceScope() }
        single { NetworkingScope() }
        viewModel { FavoriteSourcesViewModel() }
        viewModel { SourcesViewModel() }
        viewModel { NewsSearchViewModel() }
        viewModel { NewsBySourceViewModel(get()) }
    }
}