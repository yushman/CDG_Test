package com.example.cdg_test.news_api

import com.example.cdg_test.model.dto_model.ErrorResponseDto
import okhttp3.Response
import org.koin.core.KoinComponent
import retrofit2.Retrofit
import timber.log.Timber

class ErrorApiHandler(private val retrofit: Retrofit): KoinComponent{

//    private val coroutineScope by inject<NetworkingScope>()

    fun parseError(response: Response): ErrorResponseDto? {
        var error: ErrorResponseDto? =null
        val converter = retrofit.responseBodyConverter<ErrorResponseDto>(ErrorResponseDto::class.java, emptyArray())
        response.body?.let {
            error = converter.convert(it)
            Timber.e("Network Error with code ${error?.code} - ${error?.message}")
        }
//        coroutineScope.coroutineContext.cancel()

        return error
    }
}