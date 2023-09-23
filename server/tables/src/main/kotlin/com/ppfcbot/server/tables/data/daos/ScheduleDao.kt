/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package com.ppfcbot.server.tables.data.daos

import com.ppfcbot.server.tables.data.models.Schedule

internal interface ScheduleDao : TablesEntityDao<Schedule> {

    fun insert(
        groupId: Long,
        classroomId: Long,
        teacherId: Long,
        subjectId: Long?,
        eventName: String?,
        isSubject: Boolean,
        lessonNumber: Long,
        isNumerator: Boolean,
        dayNumber: Long
    ): Long

    fun update(
        id: Long,
        groupId: Long,
        classroomId: Long,
        teacherId: Long,
        subjectId: Long?,
        eventName: String?,
        isSubject: Boolean,
        lessonNumber: Long,
        isNumerator: Boolean,
        dayNumber: Long
    )

    fun getAll(
        offset: Long? = null,
        limit: Long? = null,
        dayNumber: Long? = null,
        isNumerator: Boolean? = null,
        groupId: Long? = null,
        groupNumber: Long? = null,
        teacherId: Long? = null
    ): List<Schedule>

    fun deleteAll()
}