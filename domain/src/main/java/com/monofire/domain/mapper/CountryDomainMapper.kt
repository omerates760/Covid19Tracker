package com.monofire.domain.mapper

import com.monofire.data.source.local.entity.CountryEntity
import com.monofire.domain.model.CountryDetailUiData
import com.monofire.domain.model.CountryUiData
import javax.inject.Inject

class CountryDomainMapper @Inject constructor() {

    fun convertToCountryUiData(countries: List<CountryEntity>?): List<CountryUiData> {
        return countries?.sortedBy { it.countryName }?.map {
            CountryUiData(it.countryName, it.activeCase.orEmpty(), it.newDeath)
        } ?: emptyList()
    }

    fun convertToCountryDetail(countryEntity: CountryEntity): CountryDetailUiData {
        return CountryDetailUiData(
            countryEntity.countryName,
            countryEntity.continent.orEmpty(),
            countryEntity.day,
            countryEntity.activeCase.orEmpty(),
            countryEntity.newCase.orEmpty(),
            countryEntity.newDeath.orEmpty(),
            countryEntity.totalTest.toString(),
        )
    }

}
