package com.pabloangeles.hackernewsapp.home.data.di

import com.pabloangeles.hackernewsapp.core.local.HackerNewsDatabase
import com.pabloangeles.hackernewsapp.core.local.dao.NewsHitsDao
import com.pabloangeles.hackernewsapp.home.data.services.NewHitsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class HomeModule {
  @Singleton
  @Provides
  fun provideCurrencyService(@Named("retrofit") retrofit: Retrofit): NewHitsService =
      retrofit.create(NewHitsService::class.java)

  @Singleton
  @Provides
  fun provideNewsHitsDao(@Named("hacker_news_db") db: HackerNewsDatabase): NewsHitsDao =
      db.newsHitsEntityDao()
}
