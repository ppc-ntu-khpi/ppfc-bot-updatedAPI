/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package com.ppfcbot.server.tables.data.repositories

import com.ppfcbot.common.api.models.tables.ClassroomRequest
import com.ppfcbot.common.api.models.tables.ClassroomResponse

internal interface ClassroomRepository {
    suspend fun add(classroomRequest: ClassroomRequest)
    suspend fun getAll(
        offset: Long? = null,
        limit: Long? = null,
        searchQuery: String? = null
    ): List<ClassroomResponse>
    suspend fun update(id: Long, classroomRequest: ClassroomRequest)
    suspend fun delete(id: Long)
}