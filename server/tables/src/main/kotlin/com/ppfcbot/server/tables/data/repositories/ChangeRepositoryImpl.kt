/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package com.ppfcbot.server.tables.data.repositories

import com.ppfcbot.common.api.models.tables.ChangeRequest
import com.ppfcbot.common.api.models.tables.ChangeResponse
import com.ppfcbot.server.tables.data.daos.ChangeDao
import com.ppfcbot.server.tables.data.models.Change
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal fun Change.toResponse() = ChangeResponse(
    id = id,
    groups = groups.map { it.toResponse() },
    classroom = classroom?.toResponse(),
    teacher = teacher?.toResponse(),
    subject = subject?.toResponse(),
    eventName = eventName,
    isSubject = subject != null,
    lessonNumber = lessonNumber,
    dayNumber = dayNumber,
    date = date,
    isNumerator = isNumerator
)

internal class ChangeRepositoryImpl(
    private val changeDao: ChangeDao
) : ChangeRepository {

    override suspend fun add(changeRequest: ChangeRequest): Unit = withContext(Dispatchers.IO) {
        changeDao.insert(
            groupIds = changeRequest.groupsIds,
            classroomId = changeRequest.classroomId,
            teacherId = changeRequest.teacherId,
            subjectId = changeRequest.subjectId,
            eventName = changeRequest.eventName,
            isSubject = changeRequest.subjectId != null,
            lessonNumber = changeRequest.lessonNumber,
            dayNumber = changeRequest.dayNumber,
            date = changeRequest.date,
            isNumerator = changeRequest.isNumerator
        )
    }

    override suspend fun addMultiple(changes: List<ChangeRequest>) = withContext(Dispatchers.IO) {
        for (change in changes) {
            add(change)
        }
    }

    override suspend fun getAll(
        offset: Long?,
        limit: Long?,
        date: String?,
        isNumerator: Boolean?,
        groupId: Long?,
        groupNumber: Long?,
        teacherId: Long?
    ): List<ChangeResponse> = withContext(Dispatchers.IO) {
        return@withContext changeDao.getAll(
            offset,
            limit,
            date,
            isNumerator,
            groupId,
            teacherId
        ).map { it.toResponse() }
    }

    override suspend fun update(
        id: Long,
        changeRequest: ChangeRequest
    ) = withContext(Dispatchers.IO) {
        changeDao.update(
            id = id,
            groupIds = changeRequest.groupsIds,
            classroomId = changeRequest.classroomId,
            teacherId = changeRequest.teacherId,
            subjectId = changeRequest.subjectId,
            eventName = changeRequest.eventName,
            isSubject = changeRequest.subjectId != null,
            lessonNumber = changeRequest.lessonNumber,
            dayNumber = changeRequest.dayNumber,
            date = changeRequest.date,
            isNumerator = changeRequest.isNumerator
        )
    }

    override suspend fun updateMultiple(changes: Map<Long, ChangeRequest>) = withContext(Dispatchers.IO) {
        for ((id, change) in changes) {
            update(id, change)
        }
    }

    override suspend fun delete(id: Long) = withContext(Dispatchers.IO) {
        changeDao.delete(id)
    }

    override suspend fun deleteAll() = withContext(Dispatchers.IO) {
        changeDao.deleteAll()
    }
}