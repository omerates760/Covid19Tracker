package com.monofire.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Countries")
data class CountryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo(name = "countryName")
    val countryName: String,
    @ColumnInfo(name = "continent")
    val continent: String?,
    @ColumnInfo(name = "day")
    val day: String,
    @ColumnInfo(name = "activeCase")
    val activeCase: String?,
    @ColumnInfo(name = "newCase")
    val newCase: String?,
    @ColumnInfo(name = "newDeath")
    val newDeath: String?,
    @ColumnInfo(name = "totalTest")
    val totalTest: Int,
)