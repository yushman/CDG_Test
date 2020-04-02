package com.example.cdg_test.model.dto_model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NewsDto(
    @Embedded
    val source: NewsSourceDto,
    val author: String,
    val title: String,
    val description: String,
    @PrimaryKey
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String
)