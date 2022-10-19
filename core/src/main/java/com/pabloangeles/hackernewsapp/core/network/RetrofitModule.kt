package com.pabloangeles.hackernewsapp.core.network

import com.pabloangeles.hackernewsapp.core.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

  /* A constant that is used to set the timeout for the OkHttpClient. */
  private const val TIMEOUT: Long = 30

  /** It creates an instance of HttpLoggingInterceptor and sets the level to BODY. */
  private fun loggingInterceptor(): HttpLoggingInterceptor =
      HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

  /**
   * `provideHttpClient()` returns an OkHttpClient object that has a read timeout of 10 seconds, a
   * logging interceptor, and a connection timeout of 10 seconds
   *
   * @return An OkHttpClient object
   */
  private fun provideHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .readTimeout(TIMEOUT, TimeUnit.SECONDS)
        .addInterceptor(loggingInterceptor())
        .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
        .build()
  }

  /**
   * `@Provides` is a function that returns an instance of the class that is annotated with
   * `@Singleton`
   *
   * @return Retrofit
   */
  @Singleton
  @Provides
  @Named("retrofit")
   fun provideRetrofit(): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BuildConfig.URL_BASE_API)
        .client(provideHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
  }
}
