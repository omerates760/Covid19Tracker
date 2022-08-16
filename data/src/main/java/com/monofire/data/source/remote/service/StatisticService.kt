package com.monofire.data.source.remote.service

import com.monofire.data.source.remote.model.StatisticResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface StatisticService {
    @GET("statistics")
    suspend fun getStatistics(): StatisticResponse

}