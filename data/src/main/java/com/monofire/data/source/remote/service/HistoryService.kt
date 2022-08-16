package com.monofire.data.source.remote.service

import com.monofire.data.source.remote.model.HistoryResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface HistoryService {

    @GET("history")
    suspend fun getHistory(
        @Query("country") countryName: String,
        @Query("day") day: String
    ): HistoryResponse
}