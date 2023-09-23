/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package com.ppfcbot.server.tables.data.repositories

import com.ppfcbot.common.api.models.tables.UserRequest
import com.ppfcbot.common.api.models.tables.UserResponse

internal interface UserRepository {
    suspend fun add(userRequest: UserRequest)
    suspend fun getAll(
        id: Long? = null,
        offset: Long? = null,
        limit: Long? = null,
        searchQuery: String? = null,
        isStudent: Boolean? = null
    ): List<UserResponse>
    suspend fun update(id: Long, userRequest: UserRequest)
    suspend fun delete(id: Long)
}