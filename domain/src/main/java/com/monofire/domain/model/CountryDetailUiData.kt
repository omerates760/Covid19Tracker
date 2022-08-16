package com.monofire.domain.model

data class CountryDetailUiData(
    val countryName: String,
    val continent: String,
    val day: String,
    val activeCase: String?,
    val newCase: String?,
    val newDeath: String?,
    val totalTest: String,
)