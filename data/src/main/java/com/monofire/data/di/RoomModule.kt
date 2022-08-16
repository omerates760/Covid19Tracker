package com.monofire.data.di

import android.content.Context
import com.monofire.data.source.local.db.CountryDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) = CountryDatabase.getDatabase(context)

    @Provides
    fun providesUserDao(database: CountryDatabase) = database.countryDao()

}