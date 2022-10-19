package com.pabloangeles.hackernewsapp.home.viewmodel

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.pabloangeles.hackernewsapp.core.base.BaseObservable
import com.pabloangeles.hackernewsapp.core.local.entities.NewsHitsEntity
import com.pabloangeles.hackernewsapp.core.local.entities.NewsHitsStatus
import com.pabloangeles.hackernewsapp.home.data.repository.NewHitsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewHitsViewModel
@Inject
constructor(private val newHitsRepository: NewHitsRepository, application: Application) :
    BaseObservable(application) {
  /**
   * It fetches the news from the repository and sets up the live data.
   *
   * @param noPage The page number to be fetched.
   */
  fun getNewsHits(noPage: Int) {
    viewModelScope.launch {
      newHitsRepository.getNewsHits(page = noPage).collect { setupLiveData(it) }
    }
  }

  /**
   * It updates the status of the item to DELETE.
   *
   * @param item NewsHitsEntity - the item that we want to delete
   */
  fun deleteNewHit(item: NewsHitsEntity) {
    viewModelScope.launch {
      item.status = NewsHitsStatus.DELETE
      newHitsRepository.updateToStatusDelete(item).collect { setupLiveData(it) }
    }
  }
}
