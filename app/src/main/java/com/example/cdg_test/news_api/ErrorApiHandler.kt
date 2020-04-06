package com.example.cdg_test.news_api

import com.example.cdg_test.model.dto_model.ErrorResponseDto
import okhttp3.Response
import org.koin.core.KoinComponent
import retrofit2.Retrofit
import timber.log.Timber

class ErrorApiHandler(private val retrofit: Retrofit): KoinComponent{

    fun parseError(response: Response): ErrorResponseDto? {
        val error: ErrorResponseDto? =null
        val converter = retrofit.responseBodyConverter<ErrorResponseDto>(ErrorResponseDto::class.java, emptyArray())
        response.body?.let { body ->
            converter.convert(body)?.let {
                Timber.e("Network Error with code ${it.code} - ${it.message}")
            }
        }
        return error
    }
}