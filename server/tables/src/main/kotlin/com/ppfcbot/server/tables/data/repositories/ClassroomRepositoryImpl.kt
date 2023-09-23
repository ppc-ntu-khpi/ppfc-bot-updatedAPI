/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package com.ppfcbot.server.tables.data.repositories

import com.ppfcbot.common.api.models.tables.ClassroomRequest
import com.ppfcbot.common.api.models.tables.ClassroomResponse
import com.ppfcbot.server.tables.data.daos.ClassroomDao
import com.ppfcbot.server.tables.data.models.Classroom
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal fun Classroom.toResponse() = ClassroomResponse(
    id = id,
    name = name
)

internal class ClassroomRepositoryImpl(
    private val classroomDao: ClassroomDao
) : ClassroomRepository {

    override suspend fun add(classroomRequest: ClassroomRequest): Unit = withContext(Dispatchers.IO) {
        classroomDao.insert(classroomRequest.name)
    }

    override suspend fun getAll(
        offset: Long?,
        limit: Long?,
        searchQuery: String?
    ): List<ClassroomResponse> = withContext(Dispatchers.IO) {
        return@withContext classroomDao.getAll(offset, limit, searchQuery).map { it.toResponse() }
    }

    override suspend fun update(
        id: Long,
        classroomRequest: ClassroomRequest
    ) = withContext(Dispatchers.IO) {
        classroomDao.update(id, classroomRequest.name)
    }

    override suspend fun delete(id: Long) = withContext(Dispatchers.IO) {
        classroomDao.delete(id)
    }
}