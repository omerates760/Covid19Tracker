package com.monofire.data.source.remote.model

import com.google.gson.annotations.SerializedName

data class Deaths(
    @SerializedName("1M_pop") val population: String,
    val new: String?,
    val total: Int
)