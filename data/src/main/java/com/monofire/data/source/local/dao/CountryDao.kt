package com.monofire.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.monofire.data.source.local.entity.CountryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CountryDao {

    @Query("SELECT * FROM Countries")
    fun getCountries(): Flow<List<CountryEntity>?>

    @Query("SELECT * FROM Countries WHERE countryName = :countryName")
    fun getCountryDetail(countryName: String): Flow<CountryEntity>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(entity: List<CountryEntity>)

}