/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package com.ppfcbot.server.tables.data.repositories

import com.ppfcbot.common.api.models.tables.CourseRequest
import com.ppfcbot.common.api.models.tables.CourseResponse
import com.ppfcbot.server.tables.data.daos.CourseDao
import com.ppfcbot.server.tables.data.models.Course
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal fun Course.toResponse() = CourseResponse(
    id = id,
    number = number
)

internal class CourseRepositoryImpl(
    private val courseDao: CourseDao
) : CourseRepository {

    override suspend fun add(courseRequest: CourseRequest): Unit = withContext(Dispatchers.IO) {
        courseDao.insert(courseRequest.number)
    }

    override suspend fun getAll(
        offset: Long?,
        limit: Long?,
        searchQuery: String?
    ): List<CourseResponse> = withContext(Dispatchers.IO) {
        return@withContext courseDao.getAll(offset, limit, searchQuery).map { it.toResponse() }
    }

    override suspend fun update(
        id: Long,
        courseRequest: CourseRequest
    ) = withContext(Dispatchers.IO) {
        courseDao.update(id, courseRequest.number)
    }

    override suspend fun delete(id: Long) = withContext(Dispatchers.IO) {
        courseDao.delete(id)
    }
}