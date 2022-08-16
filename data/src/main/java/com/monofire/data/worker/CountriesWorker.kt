package com.monofire.data.worker

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.monofire.data.mapper.CountryDataMapper
import com.monofire.data.repository.StatisticRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class CountriesWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters,
    private val repository: StatisticRepository,
    private val countryDataMapper: CountryDataMapper
) : CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {
        return try {
            val remoteCountries = repository.getStatisticsFromRemote()
            val mappedCountries = countryDataMapper.convertToCountryEntity(remoteCountries)
            repository.insertAll(mappedCountries)
            Log.e("gele", "worker içine girdi:$mappedCountries")
            Result.success()

        } catch (e: Exception) {
            Log.e("gele", "worker bir hata oldu" + e.message)
            Result.retry()
        }

    }

}