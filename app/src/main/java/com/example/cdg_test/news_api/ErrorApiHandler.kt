package com.example.cdg_test.news_api

import com.example.cdg_test.model.dto_model.ErrorResponseDto
import com.google.gson.Gson
import okhttp3.Response
import retrofit2.Retrofit

class ErrorApiHandler(private val retrofit: Retrofit){
    fun parseError(response: Response): ErrorResponseDto? {
        var error: ErrorResponseDto? =null
        val converter = retrofit.responseBodyConverter<ErrorResponseDto>(ErrorResponseDto::class.java, emptyArray())
        response.body?.let { error = converter.convert(it) }
        return error
    }
}