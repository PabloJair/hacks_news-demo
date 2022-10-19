package com.pabloangeles.hackernewsapp.core.local.dao

import androidx.room.*
import com.pabloangeles.hackernewsapp.core.local.entities.NewsHitsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsHitsDao {

    @Query("SELECT * FROM news_hits_entity")
     fun getAll(): Flow<List<NewsHitsEntity>>

    @Query("SELECT * FROM news_hits_entity where status == 2 LIMIT :limit OFFSET :offset")
    suspend fun getNewsHitsNotDeleted(limit: Int, offset: Int):List<NewsHitsEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(newsHitsEntities: List<NewsHitsEntity>)

    @Delete
    suspend fun delete(newsHitsEntity: NewsHitsEntity)

    @Update()
    suspend fun updateStatus(newsHitsEntity: NewsHitsEntity)
}