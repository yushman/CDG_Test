package com.example.cdg_test.manager

import com.example.cdg_test.di.NetworkingScope
import com.example.cdg_test.di.PersistanceScope
import com.example.cdg_test.model.repo.FavoriteSourcesRepo
import com.example.cdg_test.model.repo.NewsRepo
import com.example.cdg_test.model.repo.SourcesRepo
import org.koin.core.KoinComponent
import org.koin.core.inject

abstract class AbstractManager: KoinComponent {
    val persistanceScope by inject<PersistanceScope>()
    val networkingScope by inject<NetworkingScope>()
    val sourcesRepo by inject<SourcesRepo>()
    val newsRepo by inject<NewsRepo>()
    val favoriteSourcesRepo by inject<FavoriteSourcesRepo>()
}