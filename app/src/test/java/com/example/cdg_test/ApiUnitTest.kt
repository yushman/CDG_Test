package com.example.cdg_test

import com.example.cdg_test.news_api.Api
import com.example.cdg_test.news_api.NewsApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test

class ApiUnitTest {
    private val mockWebServer = MockWebServer()
    private lateinit var apiService: NewsApiService

    @Before
    fun setupTest(){
        mockWebServer.start()
        apiService = Api.createService(NewsApiService::class.java)
    }

    @After
    fun shutdownTest(){
        mockWebServer.shutdown()
    }

    @Test
    fun apiTest() {
        print(apiService.getNewsByQuery("asd").execute().body())
    }
}