package com.example.cdg_test.model.dto_model

data class NewsResponseDto(
    val status: String,
    val totalResults: Int,
    val articles: List<NewsDto>
)