/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package com.ppfcbot.server.tables.data.repositories

import com.ppfcbot.common.api.models.tables.ChangeRequest
import com.ppfcbot.common.api.models.tables.ChangeResponse

internal interface ChangeRepository {
    suspend fun add(changeRequest: ChangeRequest)
    suspend fun addMultiple(changes: List<ChangeRequest>)
    suspend fun getAll(
        offset: Long? = null,
        limit: Long? = null,
        date: String? = null,
        isNumerator: Boolean? = null,
        groupId: Long? = null,
        groupNumber: Long? = null,
        teacherId: Long? = null
    ): List<ChangeResponse>
    suspend fun update(id: Long, changeRequest: ChangeRequest)
    suspend fun updateMultiple(changes: Map<Long, ChangeRequest>)
    suspend fun delete(id: Long)
    suspend fun deleteAll()
}