package com.monofire.domain.usecase

import com.monofire.data.repository.StatisticRepository
import com.monofire.domain.mapper.CountryDomainMapper
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetCountryDetailUseCase @Inject constructor(
    private val statisticRepository: StatisticRepository,
    private val countryDomainMapper: CountryDomainMapper
) : BaseUseCase() {

    operator fun invoke(countryName: String) =
        statisticRepository.getCountryDetail(countryName)?.map {
            return@map countryDomainMapper.convertToCountryDetail(it)
        }

}