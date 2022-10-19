package com.pabloangeles.hackernewsapp.core.local

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    /**
     * It provides a database object to the dependency graph
     *
     * @param appContext Context - The application context.
     */
    @Singleton
    @Provides
    @Named("hacker_news_db")
    fun provideDatabase(@ApplicationContext appContext: Context) = HackerNewsDatabase.getDatabase(appContext)

}