/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package com.ppfcbot.server.tables.data.repositories

import com.ppfcbot.common.api.models.tables.DisciplineRequest
import com.ppfcbot.common.api.models.tables.DisciplineResponse

internal interface DisciplineRepository {
    suspend fun add(disciplineRequest: DisciplineRequest)
    suspend fun getAll(
        offset: Long? = null,
        limit: Long? = null,
        searchQuery: String? = null
    ): List<DisciplineResponse>
    suspend fun update(id: Long, disciplineRequest: DisciplineRequest)
    suspend fun delete(id: Long)
}