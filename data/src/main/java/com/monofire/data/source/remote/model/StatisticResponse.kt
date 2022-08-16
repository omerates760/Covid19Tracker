package com.monofire.data.source.remote.model

import com.google.gson.annotations.SerializedName

data class StatisticResponse(
    val errors: List<String>?,
    @SerializedName("response") val countries: List<Country>?,
    val results: Int
)