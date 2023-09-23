/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package com.ppfcbot.server.tables.data.repositories

import com.ppfcbot.common.api.models.tables.TeacherRequest
import com.ppfcbot.common.api.models.tables.TeacherResponse

internal interface TeacherRepository {
    suspend fun add(teacherRequest: TeacherRequest)
    suspend fun getAll(
        id: Long? = null,
        offset: Long? = null,
        limit: Long? = null,
        searchQuery: String? = null,
        firstName: String? = null,
        lastName: String? = null,
        disciplineId: Long? = null,
        disciplineName: String? = null
    ): List<TeacherResponse>
    suspend fun update(id: Long, teacherRequest: TeacherRequest)
    suspend fun delete(id: Long)
}