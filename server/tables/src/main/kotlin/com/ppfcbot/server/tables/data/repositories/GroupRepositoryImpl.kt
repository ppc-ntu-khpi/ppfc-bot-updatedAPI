/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package com.ppfcbot.server.tables.data.repositories

import com.ppfcbot.common.api.models.tables.GroupRequest
import com.ppfcbot.common.api.models.tables.GroupResponse
import com.ppfcbot.server.tables.data.daos.GroupDao
import com.ppfcbot.server.tables.data.models.Group
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal fun Group.toResponse() = GroupResponse(
    id = id,
    number = number,
    course = course.toResponse()
)

internal class GroupRepositoryImpl(
    private val groupDao: GroupDao
) : GroupRepository {

    override suspend fun add(groupRequest: GroupRequest): Unit = withContext(Dispatchers.IO) {
        groupDao.insert(groupRequest.number, groupRequest.courseId)
    }

    override suspend fun getAll(
        id: Long?,
        offset: Long?,
        limit: Long?,
        searchQuery: String?,
        courseId: Long?,
        courseNumber: Long?
    ): List<GroupResponse> = withContext(Dispatchers.IO) {
        return@withContext groupDao.getAll(
            id,
            offset,
            limit,
            searchQuery,
            courseId,
            courseNumber
        ).map { it.toResponse() }
    }

    override suspend fun update(
        id: Long,
        groupRequest: GroupRequest
    ) = withContext(Dispatchers.IO) {
        groupDao.update(id, groupRequest.number, groupRequest.courseId)
    }

    override suspend fun delete(id: Long) = withContext(Dispatchers.IO) {
        groupDao.delete(id)
    }
}