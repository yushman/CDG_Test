package com.example.cdg_test.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.cdg_test.model.dto_model.SourcesDto

@Dao
interface SourcesDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertSource(sourcesDto: SourcesDto)

    @Update
    fun updateSource(sourcesDto: SourcesDto)

    @Delete
    fun deleteSource(sourcesDto: SourcesDto)

    @Query("select * from sourcesdto")
    fun selectAllSources(): List<SourcesDto>

    @Query("select * from sourcesdto")
    fun loadAllSources(): LiveData<List<SourcesDto>>

    @Query("select * from sourcesdto where id like :id")
    fun selectSourceById(id: String): SourcesDto?
}