package com.example.cdg_test.model.dto_model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SourcesDto(
    @PrimaryKey
    val id: String,
    val name: String,
    val description: String,
    val url: String,
    val category: String,
    val languge: String,
    val country: String
)