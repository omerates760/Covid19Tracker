package com.monofire.data.repository

import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.monofire.data.mapper.CountryDataMapper
import com.monofire.data.source.local.StatisticsLocalDataSource
import com.monofire.data.source.local.entity.CountryEntity
import com.monofire.data.source.remote.service.StatisticService
import com.monofire.data.worker.CountriesWorker
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class StatisticRepository @Inject constructor(
    private val statisticService: StatisticService,
    private val statisticsDataSource: StatisticsLocalDataSource,
    private val countryDataMapper: CountryDataMapper,
    private val workManager: WorkManager
) {

    init {
        startCountriesWorker()
    }

    private fun startCountriesWorker() {
        val periodicRequestBuilder = PeriodicWorkRequestBuilder<CountriesWorker>(
            UPDATE_COUNTRIES_WORKER_DELAY_MIN,
            TimeUnit.MINUTES
        ).build()

        workManager.enqueueUniquePeriodicWork(
            UPDATE_COUNTRIES_WORKER,
            ExistingPeriodicWorkPolicy.KEEP,
            periodicRequestBuilder
        )

    }

    suspend fun getStatisticsFromRemote() = statisticService.getStatistics()

    suspend fun getCountriesFromLocal(): Flow<List<CountryEntity>?> {
        return statisticsDataSource.getCountries().map { localCountries ->
            if (localCountries.isNullOrEmpty()) {
                val statistics = getStatisticsFromRemote()
                val mappedCountries = countryDataMapper.convertToCountryEntity(statistics)
                insertAll(mappedCountries)
                mappedCountries
            } else {
                localCountries
            }
        }
    }

    fun getCountryDetail(countryName: String) =
        statisticsDataSource.getCountryDetail(countryName)


    suspend fun insertAll(entities: List<CountryEntity>) {
        return statisticsDataSource.insertAll(entities)
    }
}

private const val UPDATE_COUNTRIES_WORKER = "CountriesWorker"
private const val UPDATE_COUNTRIES_WORKER_DELAY_MIN = 15L