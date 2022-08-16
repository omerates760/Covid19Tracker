package com.monofire.domain.mapper

import com.monofire.data.source.remote.model.HistoryResponse
import com.monofire.domain.model.HistoryUiData
import javax.inject.Inject

class HistoryDomainMapper @Inject constructor() {

    fun convertToHistoryUiData(response: HistoryResponse): List<HistoryUiData> {
        return response.response?.sortedBy { it.day }?.map {
            HistoryUiData(it.country, it.cases.active.toString(), it.deaths.new.toString())
        } ?: emptyList()
    }

}
