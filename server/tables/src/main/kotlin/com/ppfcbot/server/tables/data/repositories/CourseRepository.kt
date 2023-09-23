/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package com.ppfcbot.server.tables.data.repositories

import com.ppfcbot.common.api.models.tables.CourseRequest
import com.ppfcbot.common.api.models.tables.CourseResponse

internal interface CourseRepository {
    suspend fun add(courseRequest: CourseRequest)
    suspend fun getAll(
        offset: Long? = null,
        limit: Long? = null,
        searchQuery: String? = null
    ): List<CourseResponse>
    suspend fun update(id: Long, courseRequest: CourseRequest)
    suspend fun delete(id: Long)
}