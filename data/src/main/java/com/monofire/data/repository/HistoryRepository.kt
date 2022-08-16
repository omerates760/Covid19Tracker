package com.monofire.data.repository

import com.monofire.data.source.remote.service.HistoryService
import javax.inject.Inject

class HistoryRepository @Inject constructor(
    private val historyService: HistoryService
) {
    suspend fun getHistory(countryName: String, day: String) =
        historyService.getHistory(countryName, day)

}