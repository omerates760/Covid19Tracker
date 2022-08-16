package com.monofire.data.mapper

import com.monofire.data.source.local.entity.CountryEntity
import com.monofire.data.source.remote.model.StatisticResponse
import javax.inject.Inject

class CountryDataMapper @Inject constructor() {
    fun convertToCountryEntity(statistics: StatisticResponse): List<CountryEntity> {
        return statistics.countries?.map {
            CountryEntity(
                countryName = it.country,
                continent = it.continent,
                day = it.day,
                activeCase = it.cases.active.toString(),
                newCase = it.cases.new,
                newDeath = it.deaths.new,
                totalTest = it.tests.total
            )
        } ?: emptyList()
    }

}