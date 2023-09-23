/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package com.ppfcbot.server.tables.data.repositories

import com.ppfcbot.common.api.models.tables.ScheduleRequest
import com.ppfcbot.common.api.models.tables.ScheduleResponse

internal interface ScheduleRepository {
    suspend fun add(scheduleRequest: ScheduleRequest)
    suspend fun addMultiple(schedule: List<ScheduleRequest>)
    suspend fun getAll(
        offset: Long? = null,
        limit: Long? = null,
        dayNumber:Long? = null,
        isNumerator: Boolean? = null,
        groupId: Long? = null,
        groupNumber: Long? = null,
        teacherId: Long? = null
    ): List<ScheduleResponse>
    suspend fun update(id: Long, scheduleRequest: ScheduleRequest)
    suspend fun updateMultiple(schedule: Map<Long, ScheduleRequest>)
    suspend fun delete(id: Long)
    suspend fun deleteAll()
}