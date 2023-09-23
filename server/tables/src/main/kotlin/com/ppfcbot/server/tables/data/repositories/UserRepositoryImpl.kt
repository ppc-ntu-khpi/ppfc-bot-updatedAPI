/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package com.ppfcbot.server.tables.data.repositories

import com.ppfcbot.common.api.models.tables.UserRequest
import com.ppfcbot.common.api.models.tables.UserResponse
import com.ppfcbot.server.tables.data.daos.UserDao
import com.ppfcbot.server.tables.data.models.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal fun User.toResponse() = UserResponse(
    id = id,
    group = group?.toResponse(),
    teacher = teacher?.toResponse(),
    isGroup = isGroup
)

internal class UserRepositoryImpl(
    private val userDao: UserDao
) : UserRepository {

    override suspend fun add(userRequest: UserRequest): Unit = withContext(Dispatchers.IO) {
        userDao.insert(
            groupId = userRequest.groupId,
            teacherId = userRequest.teacherId,
            isGroup = userRequest.groupId != null
        )
    }

    override suspend fun getAll(
        id: Long?,
        offset: Long?,
        limit: Long?,
        searchQuery: String?,
        isStudent: Boolean?
    ): List<UserResponse> = withContext(Dispatchers.IO) {
        return@withContext userDao.getAll(id, offset, limit, searchQuery, isStudent).map { it.toResponse() }
    }

    override suspend fun update(
        id: Long,
        userRequest: UserRequest
    ) = withContext(Dispatchers.IO) {
        userDao.update(
            id = id,
            groupId = userRequest.groupId,
            teacherId = userRequest.teacherId,
            isGroup = userRequest.groupId != null
        )
    }

    override suspend fun delete(id: Long) = withContext(Dispatchers.IO) {
        userDao.delete(id)
    }
}