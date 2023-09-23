/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package com.ppfcbot.server.tables.data.repositories

import com.ppfcbot.common.api.models.tables.GroupRequest
import com.ppfcbot.common.api.models.tables.GroupResponse

internal interface GroupRepository {
    suspend fun add(groupRequest: GroupRequest)
    suspend fun getAll(
        id: Long? = null,
        offset: Long? = null,
        limit: Long? = null,
        searchQuery: String? = null,
        courseId: Long? = null,
        courseNumber: Long? = null
    ): List<GroupResponse>
    suspend fun update(id: Long, groupRequest: GroupRequest)
    suspend fun delete(id: Long)
}