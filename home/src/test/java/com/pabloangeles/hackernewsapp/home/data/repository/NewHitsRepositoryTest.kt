package com.pabloangeles.hackernewsapp.home.data.repository

import com.pabloangeles.hackernewsapp.core.local.dao.NewsHitsDao
import com.pabloangeles.hackernewsapp.core.local.entities.NewsHitsEntity
import com.pabloangeles.hackernewsapp.core.local.entities.NewsHitsStatus
import com.pabloangeles.hackernewsapp.core.network.NetworkResult
import com.pabloangeles.hackernewsapp.home.data.models.ListNewHitsModel
import com.pabloangeles.hackernewsapp.home.data.remote.NewHitsRemoteDataSource
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class NewHitsRepositoryTest {

  @RelaxedMockK private lateinit var remoteDataSource: NewHitsRemoteDataSource

  @RelaxedMockK private lateinit var localDataSource: NewsHitsDao

  private lateinit var newHitsRepository: NewHitsRepository
  @Before
  fun onBefore() {
    MockKAnnotations.init(this)
    newHitsRepository = NewHitsRepository(remoteDataSource, localDataSource)
  }
  @Test
  fun `When The Api New Hits Return Any Thing GeValues From Database`() = runBlocking {
    // Given
    coEvery { remoteDataSource.getNewHits(page = 1) } returns
        NetworkResult.Success(
            ListNewHitsModel(
                exhaustiveNbHits = false,
                exhaustiveTypo = false,
                hits = arrayListOf(),
                hitsPerPage = 10,
                nbHits = 1,
                nbPages = 1,
                page = 1,
                params = "",
                processingTimeMS = 1,
                query = ""))
    // When
    val flow = newHitsRepository.getNewsHits(page = 1)
    // then
    flow.collect {
      if (it is NetworkResult.Success) {
        assert(it.data?.isEmpty() ?: false) { println(it) }
      }
    }
  }
  @Test
  fun `When The BaseData no contains Data return empty list `() = runBlocking {
    // Given
    coEvery { localDataSource.getAll() } returns flow { emit(emptyList()) }
    coEvery { remoteDataSource.getNewHits(page = 1) } returns
        NetworkResult.Success(
            ListNewHitsModel(
                exhaustiveNbHits = false,
                exhaustiveTypo = false,
                hits = arrayListOf(),
                hitsPerPage = 10,
                nbHits = 1,
                nbPages = 1,
                page = 1,
                params = "",
                processingTimeMS = 1,
                query = ""))
    // When
    val flow = newHitsRepository.getNewsHits(page = 1)
    // then
    flow.collect {
      if (it is NetworkResult.Success) {
        assert(it.data?.isEmpty() ?: false) { println(it) }
      }
    }
  }

  @Test
  fun `When delete a Hits `() = runBlocking {
    val data =
        NewsHitsEntity(
            111,
            111,
            "TEST",
            "Coment",
            "12-12-12T12:33:22.000Z",
            2,
            2,
            "",
            "",
            "",
            "",
            "",
            NewsHitsStatus.DELETE)
    // Given

    // When
    val flow = newHitsRepository.updateToStatusDelete(data)
    // then
    flow.collect {
      if (it is NetworkResult.Success) {
        assert(it != null) { println(it) }
      }
    }
  }

  @Test
  fun `When add a Hits `() = runBlocking {
    val data =
        NewsHitsEntity(
            111,
            111,
            "TEST",
            "Coment",
            "12-12-12T12:33:22.000Z",
            2,
            2,
            "",
            "",
            "",
            "",
            "",
            NewsHitsStatus.NEW)
    // Given

    // When
    val flow = localDataSource.insertAll(arrayListOf(data))
    // then

    assert(true)
  }
}
