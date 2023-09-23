/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package com.ppfcbot.server.auxiliary.data.bellschedule

import com.ppfcbot.common.api.models.auxiliary.BellScheduleRequest
import com.ppfcbot.common.api.models.auxiliary.BellScheduleResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal fun BellSchedule.toResponse() = BellScheduleResponse(text)
internal fun BellScheduleRequest.toModel() = BellSchedule(text)

internal class BellScheduleRepositoryImpl(
    private val bellScheduleDao: BellScheduleDao
) : BellScheduleRepository {

    override suspend fun add(bellScheduleRequest: BellScheduleRequest): Unit = withContext(Dispatchers.IO) {
        bellScheduleDao.insert(bellScheduleRequest.toModel())
    }

    override suspend fun get(): BellScheduleResponse? = withContext(Dispatchers.IO) {
        return@withContext bellScheduleDao.get()?.toResponse()
    }

    override suspend fun delete(): Unit = withContext(Dispatchers.IO) {
        bellScheduleDao.delete()
    }
}