/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package com.ppfcbot.server.tables.data.daos

import com.ppfcbot.server.tables.data.models.Change

internal interface ChangeDao : TablesEntityDao<Change> {

    fun insert(
        groupIds: List<Long>,
        classroomId: Long?,
        teacherId: Long?,
        subjectId: Long?,
        eventName: String?,
        isSubject: Boolean,
        lessonNumber: Long?,
        date: String,
        isNumerator: Boolean,
        dayNumber: Long
    ): Long

    fun update(
        id: Long,
        groupIds: List<Long>,
        classroomId: Long?,
        teacherId: Long?,
        subjectId: Long?,
        eventName: String?,
        isSubject: Boolean,
        lessonNumber: Long?,
        date: String,
        isNumerator: Boolean,
        dayNumber: Long
    )

    fun getAll(
        offset: Long? = null,
        limit: Long? = null,
        date: String? = null,
        isNumerator: Boolean? = null,
        groupId: Long? = null,
        teacherId: Long? = null
    ): List<Change>

    fun deleteAll()
}