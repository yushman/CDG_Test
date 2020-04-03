package com.example.cdg_test.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.cdg_test.model.dto_model.NewsDto

@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertNews(newsDto: NewsDto)

    @Update
    fun updateNews(newsDto: NewsDto)

    @Delete
    fun deleteNews(newsDto: NewsDto)

    @Query("select * from newsdto")
    fun selectAllNews(): List<NewsDto>

    @Query("select * from newsdto")
    fun loadAllNews(): LiveData<List<NewsDto>>

    @Query("select * from newsdto where id like :sourceId")
    fun loadNewsBySource(sourceId: String): LiveData<List<NewsDto>>
}