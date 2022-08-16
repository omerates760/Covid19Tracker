package com.monofire.data.source.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.monofire.data.source.local.dao.CountryDao
import com.monofire.data.source.local.entity.CountryEntity

@Database(
    entities = [CountryEntity::class],
    version = 1,
    exportSchema = false
)
abstract class CountryDatabase : RoomDatabase() {

    abstract fun countryDao(): CountryDao

    companion object {
        @Volatile
        private var instance: CountryDatabase? = null
        fun getDatabase(context: Context): CountryDatabase =
            instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also {
                    instance = it
                }
            }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, CountryDatabase::class.java, "CountriesDatabase")
                .fallbackToDestructiveMigration()
                .build()
    }

}