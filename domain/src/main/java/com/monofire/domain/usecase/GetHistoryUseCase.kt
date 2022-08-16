package com.monofire.domain.usecase

import com.monofire.data.repository.HistoryRepository
import com.monofire.domain.mapper.HistoryDomainMapper
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetHistoryUseCase @Inject constructor(
    private val historyRepository: HistoryRepository,
    private val historyDomainMapper: HistoryDomainMapper
) : BaseUseCase() {

    suspend operator fun invoke(countryName: String, day: String) = flow {
        val response = historyRepository.getHistory(countryName, day)
        val mappedResponse = historyDomainMapper.convertToHistoryUiData(response)
        emit(mappedResponse)
    }
}