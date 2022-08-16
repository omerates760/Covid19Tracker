package com.monofire.data.di

import com.monofire.data.BuildConfig
import com.monofire.data.source.remote.service.HistoryService
import com.monofire.data.source.remote.service.StatisticService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        tokenInterceptor: Interceptor,
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(tokenInterceptor)
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideTokenInterceptor(): Interceptor {
        return Interceptor {
            val request = it.request().newBuilder()
                .addHeader(API_KEY, BuildConfig.API_KEY)
                .addHeader(API_HOST, BuildConfig.API_HOST)
            val actualRequest = request.build()
            it.proceed(actualRequest)
        }

    }


    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BASIC
        }

    }

    @Provides
    @Singleton
    fun provideCountryService(retrofit: Retrofit): StatisticService {
        return retrofit.create(StatisticService::class.java)
    }

    @Provides
    @Singleton
    fun provideHistoryService(retrofit: Retrofit): HistoryService {
        return retrofit.create(HistoryService::class.java)
    }
}

private const val CONNECT_TIMEOUT = 20L
private const val READ_TIMEOUT = 60L
private const val WRITE_TIMEOUT = 120L

private const val API_KEY = "X-RapidAPI-Key"
private const val API_HOST = "X-RapidAPI-Host"