package com.pabloangeles.hackernewsapp.home.data.services

import com.pabloangeles.hackernewsapp.home.data.models.ListNewHitsModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/* A service that will return a list of new hits model from the API. */
interface NewHitsService {
  /**
   * This function will return a list of new hits model from the API
   *
   * @param query The search query.
   * @param hitsPerPage The number of results to return per page.
   * @param page The page number to fetch.
   */
  @GET(EndPoints.NEWS_HITS)
  suspend fun getHits(
      @Query("query") query: String = "android",
      @Query("hitsPerPage") hitsPerPage: Int = 10,
      @Query("page") page: Int
  ): Response<ListNewHitsModel>
}
