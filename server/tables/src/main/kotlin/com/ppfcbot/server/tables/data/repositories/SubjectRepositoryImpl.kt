/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package com.ppfcbot.server.tables.data.repositories

import com.ppfcbot.common.api.models.tables.SubjectRequest
import com.ppfcbot.common.api.models.tables.SubjectResponse
import com.ppfcbot.server.tables.data.daos.SubjectDao
import com.ppfcbot.server.tables.data.models.Subject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal fun Subject.toResponse() = SubjectResponse(
    id = id,
    name = name
)

internal class SubjectRepositoryImpl(
    private val subjectDao: SubjectDao
) : SubjectRepository {

    override suspend fun add(subjectRequest: SubjectRequest): Unit = withContext(Dispatchers.IO) {
        subjectDao.insert(subjectRequest.name)
    }

    override suspend fun getAll(
        offset: Long?,
        limit: Long?,
        searchQuery: String?
    ): List<SubjectResponse> = withContext(Dispatchers.IO) {
        return@withContext subjectDao.getAll(offset, limit, searchQuery).map { it.toResponse() }
    }

    override suspend fun update(
        id: Long,
        subjectRequest: SubjectRequest
    ) = withContext(Dispatchers.IO) {
        subjectDao.update(id, subjectRequest.name)
    }

    override suspend fun delete(id: Long) = withContext(Dispatchers.IO) {
        subjectDao.delete(id)
    }
}