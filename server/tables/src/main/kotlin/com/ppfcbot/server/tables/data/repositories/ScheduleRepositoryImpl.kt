/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package com.ppfcbot.server.tables.data.repositories

import com.ppfcbot.common.api.models.tables.ScheduleRequest
import com.ppfcbot.common.api.models.tables.ScheduleResponse
import com.ppfcbot.server.tables.data.daos.ScheduleDao
import com.ppfcbot.server.tables.data.models.Schedule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal fun Schedule.toResponse() = ScheduleResponse(
    id = id,
    group = group.toResponse(),
    classroom = classroom.toResponse(),
    teacher = teacher.toResponse(),
    subject = subject?.toResponse(),
    eventName = eventName,
    isSubject = subject != null,
    lessonNumber = lessonNumber,
    dayNumber = dayNumber,
    isNumerator = isNumerator
)

internal class ScheduleRepositoryImpl(
    private val scheduleDao: ScheduleDao
) : ScheduleRepository {

    override suspend fun add(scheduleRequest: ScheduleRequest): Unit = withContext(Dispatchers.IO) {
        scheduleDao.insert(
            groupId = scheduleRequest.groupId,
            classroomId = scheduleRequest.classroomId,
            teacherId = scheduleRequest.teacherId,
            subjectId = scheduleRequest.subjectId,
            eventName = scheduleRequest.eventName,
            isSubject = scheduleRequest.subjectId != null,
            lessonNumber = scheduleRequest.lessonNumber,
            dayNumber = scheduleRequest.dayNumber,
            isNumerator = scheduleRequest.isNumerator
        )
    }

    override suspend fun addMultiple(schedule: List<ScheduleRequest>) = withContext(Dispatchers.IO) {
        for (scheduleItem in schedule) {
            add(scheduleItem)
        }
    }

    override suspend fun getAll(
        offset: Long?,
        limit: Long?,
        dayNumber: Long?,
        isNumerator: Boolean?,
        groupId: Long?,
        groupNumber: Long?,
        teacherId: Long?
    ): List<ScheduleResponse> = withContext(Dispatchers.IO) {
        return@withContext scheduleDao.getAll(
            offset,
            limit,
            dayNumber,
            isNumerator,
            groupId,
            groupNumber,
            teacherId
        ).map { it.toResponse() }
    }

    override suspend fun update(
        id: Long,
        scheduleRequest: ScheduleRequest
    ) = withContext(Dispatchers.IO) {
        scheduleDao.update(
            id = id,
            groupId = scheduleRequest.groupId,
            classroomId = scheduleRequest.classroomId,
            teacherId = scheduleRequest.teacherId,
            subjectId = scheduleRequest.subjectId,
            eventName = scheduleRequest.eventName,
            isSubject = scheduleRequest.subjectId != null,
            lessonNumber = scheduleRequest.lessonNumber,
            dayNumber = scheduleRequest.dayNumber,
            isNumerator = scheduleRequest.isNumerator
        )
    }

    override suspend fun updateMultiple(schedule: Map<Long, ScheduleRequest>) = withContext(Dispatchers.IO) {
        for ((id, scheduleItem) in schedule) {
            update(id, scheduleItem)
        }
    }

    override suspend fun delete(id: Long) = withContext(Dispatchers.IO) {
        scheduleDao.delete(id)
    }

    override suspend fun deleteAll() = withContext(Dispatchers.IO) {
        scheduleDao.deleteAll()
    }
}