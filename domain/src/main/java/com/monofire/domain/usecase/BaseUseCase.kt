package com.monofire.domain.usecase

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

open class BaseUseCase : CoroutineScope {
    private var parentJob = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + parentJob
}