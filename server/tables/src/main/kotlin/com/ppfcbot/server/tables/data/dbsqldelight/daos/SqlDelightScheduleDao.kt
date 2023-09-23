/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package com.ppfcbot.server.tables.data.dbsqldelight.daos

import com.ppfcbot.server.tables.data.daos.ScheduleDao
import com.ppfcbot.server.tables.data.dbsqldelight.utils.toBoolean
import com.ppfcbot.server.tables.data.dbsqldelight.utils.toSqlBoolean
import com.ppfcbot.server.tables.data.models.*
import com.ppfcbot.server.tables.database.TablesDatabase
import com.ppfcbot.server.tables.database.schedule.SelectWithParameters

internal class SqlDelightScheduleDao(
    override val db: TablesDatabase
) : ScheduleDao, SqlDelightEntityDao<Schedule> {

    override fun insert(entity: Schedule): Long = insert(
        groupId = entity.group.id,
        classroomId = entity.classroom.id,
        teacherId = entity.teacher.id,
        subjectId = entity.subject?.id,
        eventName = entity.eventName,
        isSubject = entity.isSubject,
        lessonNumber = entity.lessonNumber,
        dayNumber = entity.dayNumber,
        isNumerator = entity.isNumerator
    )

    override fun insert(
        groupId: Long,
        classroomId: Long,
        teacherId: Long,
        subjectId: Long?,
        eventName: String?,
        isSubject: Boolean,
        lessonNumber: Long,
        isNumerator: Boolean,
        dayNumber: Long
    ): Long {
        db.scheduleQueries.insert(
            id = null,
            groupId = groupId,
            classroomId = classroomId,
            teacherId = teacherId,
            subjectId = subjectId,
            eventName = eventName,
            isSubject = isSubject.toSqlBoolean(),
            lessonNumber = lessonNumber,
            dayNumber = dayNumber,
            isNumerator = isNumerator.toSqlBoolean()
        )

        return db.scheduleQueries.lastInsertRowId().executeAsOne()
    }

    override fun update(entity: Schedule) = update(
        id = entity.id,
        groupId = entity.group.id,
        classroomId = entity.classroom.id,
        teacherId = entity.teacher.id,
        subjectId = entity.subject?.id,
        eventName = entity.eventName,
        isSubject = entity.isSubject,
        lessonNumber = entity.lessonNumber,
        dayNumber = entity.dayNumber,
        isNumerator = entity.isNumerator
    )

    override fun update(
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
    ) {
        db.scheduleQueries.updateWhereId(
            groupId = groupId,
            classroomId = classroomId,
            teacherId = teacherId,
            subjectId = subjectId,
            eventName = eventName,
            isSubject = isSubject.toSqlBoolean(),
            lessonNumber = lessonNumber,
            dayNumber = dayNumber,
            isNumerator = isNumerator.toSqlBoolean(),
            id = id
        )
    }

    override fun delete(id: Long) {
        db.scheduleQueries.deleteWhereId(id)
    }

    override fun getAll(
        offset: Long?,
        limit: Long?,
        dayNumber: Long?,
        isNumerator: Boolean?,
        groupId: Long?,
        groupNumber: Long?,
        teacherId: Long?
    ): List<Schedule> {
        return db.scheduleQueries.selectWithParameters(
            offset = offset,
            limit = limit,
            dayNumber = dayNumber,
            isNumerator = isNumerator?.toSqlBoolean(),
            groupId = groupId,
            groupNumber = groupNumber,
            teacherId = teacherId
        ).executeAsList().mapScheduleEntries()
    }

    private fun List<SelectWithParameters>.mapScheduleEntries(): List<Schedule> = this.mapNotNull { entry ->
        val course = if (entry.courseId != null && entry.courseNumber != null) {
            Course(
                id = entry.courseId,
                number = entry.courseNumber
            )
        } else return@mapNotNull null

        val group = if (entry.groupId != null && entry.groupNumber != null) {
            Group(
                id = entry.groupId,
                number = entry.groupNumber,
                course = course
            )
        } else return@mapNotNull null

        val classroom = if (entry.classroomId != null && entry.classroomName != null) {
            Classroom(
                id = entry.classroomId,
                name = entry.classroomName
            )
        } else return@mapNotNull null

        val discipline = if (entry.disciplineId != null && entry.disciplineName != null) {
            Discipline(
                id = entry.disciplineId,
                name = entry.disciplineName
            )
        } else return@mapNotNull null

        val teacher = if (entry.teacherId != null && entry.teacherIsHeadTeacher != null &&
            entry.teacherFirstName != null && entry.teacherLastName != null &&
            entry.teacherMiddleName != null
        ) {
            Teacher(
                id = entry.teacherId,
                firstName = entry.teacherFirstName,
                lastName = entry.teacherLastName,
                middleName = entry.teacherMiddleName,
                discipline = discipline,
                isHeadTeacher = entry.teacherIsHeadTeacher.toBoolean()
            )
        } else return@mapNotNull null

        val subject = if (entry.subjectId != null && entry.subjectName != null) {
            Subject(
                id = entry.subjectId,
                name = entry.subjectName
            )
        } else null

        Schedule(
            id = entry.id,
            group = group,
            classroom = classroom,
            teacher = teacher,
            subject = subject,
            eventName = entry.eventName,
            isSubject = entry.isSubject.toBoolean(),
            lessonNumber = entry.lessonNumber,
            dayNumber = entry.dayNumber,
            isNumerator = entry.isNumerator.toBoolean(),
        )
    }

    override fun deleteAll() {
        db.scheduleQueries.deleteAll()
    }
}