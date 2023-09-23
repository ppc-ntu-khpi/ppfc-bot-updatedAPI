/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package com.ppfcbot.server.tables.data.repositories

import com.ppfcbot.common.api.models.tables.SubjectRequest
import com.ppfcbot.common.api.models.tables.SubjectResponse

internal interface SubjectRepository {
    suspend fun add(subjectRequest: SubjectRequest)
    suspend fun getAll(
        offset: Long? = null,
        limit: Long? = null,
        searchQuery: String? = null
    ): List<SubjectResponse>
    suspend fun update(id: Long, subjectRequest: SubjectRequest)
    suspend fun delete(id: Long)
}