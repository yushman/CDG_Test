package com.example.cdg_test.model.item_model

import com.example.cdg_test.model.dto_model.SourcesDto

data class SourcesItem(
    val source: SourcesDto,
    val isInFavorites: Boolean = false
)