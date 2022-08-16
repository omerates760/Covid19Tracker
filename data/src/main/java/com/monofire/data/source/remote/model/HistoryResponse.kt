package com.monofire.data.source.remote.model

data class HistoryResponse(
    val errors: List<String>?,
    val response: List<Country>?,
    val results: Int
)