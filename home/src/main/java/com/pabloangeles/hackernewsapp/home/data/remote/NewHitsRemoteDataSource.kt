package com.pabloangeles.hackernewsapp.home.data.remote

import com.pabloangeles.hackernewsapp.core.base.BaseApiResponse
import com.pabloangeles.hackernewsapp.home.data.services.NewHitsService
import javax.inject.Inject

/* A class that is responsible for making the API call to the remote service. */
class NewHitsRemoteDataSource @Inject constructor(private val remote: NewHitsService) :
    BaseApiResponse() {
  /**
   * `getNewHits` is a suspend function that calls `safeApiCall` which calls `remote.getDemosApps`
   * which is a suspend function that returns a `Result<List<Hit>>`
   *
   * @param query The search query.
   * @param hitsPerPage The number of results to return per page.
   * @param page The page number to fetch.
   */
  suspend fun getNewHits(query: String = "android", hitsPerPage: Int = 10, page: Int) =
      safeApiCall {
        remote.getHits(query, hitsPerPage, page)
      }
}
