package com.pabloangeles.hackernewsapp.home.data.repository

import com.pabloangeles.hackernewsapp.core.local.dao.NewsHitsDao
import com.pabloangeles.hackernewsapp.core.local.entities.NewsHitsEntity
import com.pabloangeles.hackernewsapp.core.local.entities.NewsHitsStatus
import com.pabloangeles.hackernewsapp.core.network.NetworkResult
import com.pabloangeles.hackernewsapp.core.utils.performGetOperationDB
import com.pabloangeles.hackernewsapp.home.data.remote.NewHitsRemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NewHitsRepository
@Inject
constructor(
    private val remoteDataSource: NewHitsRemoteDataSource,
    private val localDataSource: NewsHitsDao
) {

  /**
   * It performs a get operation on the database and if the database is empty, it performs a network
   * call and saves the result in the database.
   *
   * @param query The search query.
   * @param hitsPerPage The number of hits to return per page.
   * @param page The page number to load.
   */
  fun getNewsHits(query: String = "android", hitsPerPage: Int = 10, page: Int) =
      performGetOperationDB(
          databaseQuery = {
            localDataSource.getNewsHitsNotDeleted(
                hitsPerPage, (if (page == 1) 1 else (hitsPerPage * (page - 1))))
          },
          networkCall = { remoteDataSource.getNewHits(query, hitsPerPage, page) },
          saveCallResult = { listNewHitsModel ->
            listNewHitsModel?.let {
              localDataSource.insertAll(
                  listNewHitsModel.hits.map { hit ->
                    NewsHitsEntity(
                        hit.objectID,
                        hit.storyId,
                        hit.author ?: "",
                        hit.commentText ?: "",
                        hit.createdAt ?: "",
                        hit.numComments ?: 0,
                        hit.points ?: 0,
                        hit.storyText,
                        hit.storyTitle ?: "",
                        hit.storyUrl ?: "",
                        hit.title ?: "",
                        hit.url ?: "",
                        NewsHitsStatus.NEW)
                  })
            }
          })

  /**
   * It updates the status of the news to deleted.
   *
   * @param newsHitsEntity NewsHitsEntity
   */
  fun updateToStatusDelete(newsHitsEntity: NewsHitsEntity): Flow<NetworkResult<NewsHitsEntity>> =
      flow {
            emit(NetworkResult.Loading(true))
            localDataSource.updateStatus(newsHitsEntity)
            emit(NetworkResult.Success(newsHitsEntity))
            emit(NetworkResult.Loading(false))
          }
          .flowOn(Dispatchers.IO)
}
