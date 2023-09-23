/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package com.ppfcbot.server.auxiliary.data.workingsaturdays

import com.ppfcbot.common.api.models.auxiliary.WorkingSaturdaysRequest
import com.ppfcbot.common.api.models.auxiliary.WorkingSaturdaysResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal fun WorkingSaturdays.toResponse() = WorkingSaturdaysResponse(text)
internal fun WorkingSaturdaysRequest.toModel() = WorkingSaturdays(text)

internal class WorkingSaturdaysRepositoryImpl(
    private val workingSaturdaysDao: WorkingSaturdaysDao
) : WorkingSaturdaysRepository {

    override suspend fun add(workingSaturdaysRequest: WorkingSaturdaysRequest): Unit = withContext(Dispatchers.IO) {
        workingSaturdaysDao.insert(workingSaturdaysRequest.toModel())
    }

    override suspend fun get(): WorkingSaturdaysResponse? = withContext(Dispatchers.IO) {
        return@withContext workingSaturdaysDao.get()?.toResponse()
    }

    override suspend fun delete(): Unit = withContext(Dispatchers.IO) {
        workingSaturdaysDao.delete()
    }
}