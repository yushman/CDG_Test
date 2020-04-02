package com.example.cdg_test.model.dto_model

data class ResponseDto <T>(
    val status: Boolean,
    val payload: List<T>,
    val totalResults: Int = 0
)