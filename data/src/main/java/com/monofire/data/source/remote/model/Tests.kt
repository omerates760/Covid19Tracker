package com.monofire.data.source.remote.model

import com.google.gson.annotations.SerializedName

data class Tests(
    @SerializedName("1M_pop") val population: String,
    val total: Int
)