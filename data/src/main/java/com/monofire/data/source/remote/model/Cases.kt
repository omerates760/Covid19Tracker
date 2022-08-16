package com.monofire.data.source.remote.model

import com.google.gson.annotations.SerializedName

data class Cases(
    @SerializedName("1M_pop") val population: String,
    val active: Int?,
    val critical: Int?,
    val new: String?,
    val recovered: Int?,
    val total: Int
)