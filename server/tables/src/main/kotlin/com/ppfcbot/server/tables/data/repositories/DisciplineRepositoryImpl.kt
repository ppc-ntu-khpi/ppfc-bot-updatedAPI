/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package com.ppfcbot.server.tables.data.repositories

import com.ppfcbot.common.api.models.tables.DisciplineRequest
import com.ppfcbot.common.api.models.tables.DisciplineResponse
import com.ppfcbot.server.tables.data.daos.DisciplineDao
import com.ppfcbot.server.tables.data.models.Discipline
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal fun Discipline.toResponse() = DisciplineResponse(
    id = id,
    name = name
)

internal class DisciplineRepositoryImpl(
    private val disciplineDao: DisciplineDao
) : DisciplineRepository {

    override suspend fun add(disciplineRequest: DisciplineRequest): Unit = withContext(Dispatchers.IO) {
        disciplineDao.insert(disciplineRequest.name)
    }

    override suspend fun getAll(
        offset: Long?,
        limit: Long?,
        searchQuery: String?
    ): List<DisciplineResponse> = withContext(Dispatchers.IO) {
        return@withContext disciplineDao.getAll(offset, limit, searchQuery).map { it.toResponse() }
    }

    override suspend fun update(
        id: Long,
        disciplineRequest: DisciplineRequest
    ) = withContext(Dispatchers.IO) {
        disciplineDao.update(id, disciplineRequest.name)
    }

    override suspend fun delete(id: Long) = withContext(Dispatchers.IO) {
        disciplineDao.delete(id)
    }
}