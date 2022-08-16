package com.monofire.data.source.local

import com.monofire.data.source.local.dao.CountryDao
import com.monofire.data.source.local.entity.CountryEntity
import javax.inject.Inject

class StatisticsLocalDataSource @Inject constructor(
    private val countryDao: CountryDao
) {

    suspend fun insertAll(entities: List<CountryEntity>) {
        return countryDao.insertAll(entities)
    }

    suspend fun getCountries() = countryDao.getCountries()

    fun getCountryDetail(countryName: String) = countryDao.getCountryDetail(countryName)

}