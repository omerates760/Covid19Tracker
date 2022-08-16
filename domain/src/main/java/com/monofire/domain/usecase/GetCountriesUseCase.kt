package com.monofire.domain.usecase

import com.monofire.data.repository.StatisticRepository
import com.monofire.domain.mapper.CountryDomainMapper
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetCountriesUseCase @Inject constructor(
    private val statisticRepository: StatisticRepository,
    private val countryDomainMapper: CountryDomainMapper
) : BaseUseCase() {

    suspend operator fun invoke() = statisticRepository.getCountriesFromLocal().map {
        return@map countryDomainMapper.convertToCountryUiData(it)
    }

}