/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package com.ppfcbot.server.tables.data.dbsqldelight.daos

import com.ppfcbot.server.tables.data.daos.ChangeDao
import com.ppfcbot.server.tables.data.dbsqldelight.utils.toBoolean
import com.ppfcbot.server.tables.data.dbsqldelight.utils.toSqlBoolean
import com.ppfcbot.server.tables.data.models.*
import com.ppfcbot.server.tables.database.TablesDatabase
import com.ppfcbot.server.tables.database.change.SelectWithParameters

internal class SqlDelightChangeDao(
    override val db: TablesDatabase
) : ChangeDao, SqlDelightEntityDao<Change> {

    private fun insertGroupsWhereChange(changeId: Long, groupIds: List<Long>) {
        for (groupId in groupIds) {
            db.changeDtoAndGroupDtoQueries.insert(
                changeId = changeId,
                groupId = groupId
            )
        }
    }

    override fun insert(entity: Change): Long = insert(
        groupIds = entity.groups.map { it.id },
        classroomId = entity.classroom?.id,
        teacherId = entity.teacher?.id,
        subjectId = entity.subject?.id,
        eventName = entity.eventName,
        isSubject = entity.isSubject,
        lessonNumber = entity.lessonNumber,
        dayNumber = entity.dayNumber,
        date = entity.date,
        isNumerator = entity.isNumerator
    )

    override fun insert(
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
    ): Long {
        db.changeQueries.insert(
            id = null,
            classroomId = classroomId,
            teacherId = teacherId,
            subjectId = subjectId,
            eventName = eventName,
            isSubject = isSubject.toSqlBoolean(),
            lessonNumber = lessonNumber,
            dayNumber = dayNumber,
            date = date,
            isNumerator = isNumerator.toSqlBoolean()
        )

        val lastInsertedIndex = db.changeQueries.lastInsertRowId().executeAsOne().lastId!!

        insertGroupsWhereChange(lastInsertedIndex, groupIds)

        return lastInsertedIndex
    }

    override fun update(entity: Change) = update(
        groupIds = entity.groups.map { it.id },
        classroomId = entity.classroom?.id,
        teacherId = entity.teacher?.id,
        subjectId = entity.subject?.id,
        eventName = entity.eventName,
        isSubject = entity.isSubject,
        lessonNumber = entity.lessonNumber,
        dayNumber = entity.dayNumber,
        date = entity.date,
        isNumerator = entity.isNumerator,
        id = entity.id
    )

    override fun update(
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
    ) {
        db.changeQueries.updateWhereId(
            classroomId = classroomId,
            teacherId = teacherId,
            subjectId = subjectId,
            eventName = eventName,
            isSubject = isSubject.toSqlBoolean(),
            lessonNumber = lessonNumber,
            dayNumber = dayNumber,
            date = date,
            isNumerator = isNumerator.toSqlBoolean(),
            id = id
        )

        db.changeDtoAndGroupDtoQueries.deleteWhereChangeId(id)
        insertGroupsWhereChange(id, groupIds)
    }

    override fun delete(id: Long) {
        db.changeQueries.deleteWhereId(id)
    }

    override fun getAll(
        offset: Long?,
        limit: Long?,
        date: String?,
        isNumerator: Boolean?,
        groupId: Long?,
        teacherId: Long?
    ): List<Change> {
        return db.changeQueries.selectWithParameters(
            offset = offset,
            limit = limit,
            date = date,
            isNumerator = isNumerator?.toSqlBoolean(),
            groupId = groupId,
            teacherId = teacherId
        ).executeAsList().mapChangeEntries()
    }

    private fun List<SelectWithParameters>.mapChangeEntries(): List<Change> = this.groupBy {
        it.id
    }.mapNotNull { changeEntries ->
        val groups = changeEntries.value.mapNotNull innerMapNotNull@{ entry ->
            val course = if (entry.courseId != null && entry.courseNumber != null) {
                Course(
                    id = entry.courseId,
                    number = entry.courseNumber
                )
            } else return@innerMapNotNull null

            if (entry.groupId != null && entry.groupNumber != null) {
                Group(
                    id = entry.groupId,
                    number = entry.groupNumber,
                    course = course
                )
            } else return@innerMapNotNull null
        }

        val entry = changeEntries.value.first()

        val classroom = if (entry.classroomId != null && entry.classroomName != null) {
            Classroom(
                id = entry.classroomId,
                name = entry.classroomName
            )
        } else null

        val discipline = if (entry.disciplineId != null && entry.disciplineName != null) {
            Discipline(
                id = entry.disciplineId,
                name = entry.disciplineName
            )
        } else null

        val teacher = if (discipline != null && entry.teacherId != null && entry.teacherIsHeadTeacher != null &&
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
        } else null

        val subject = if (entry.subjectId != null && entry.subjectName != null) {
            Subject(
                id = entry.subjectId,
                name = entry.subjectName
            )
        } else null

        Change(
            id = entry.id,
            groups = groups,
            classroom = classroom,
            teacher = teacher,
            subject = subject,
            eventName = entry.eventName,
            isSubject = entry.isSubject.toBoolean(),
            lessonNumber = entry.lessonNumber,
            date = entry.date,
            isNumerator = entry.isNumerator.toBoolean(),
            dayNumber = entry.dayNumber
        )
    }

    override fun deleteAll() {
        db.changeQueries.deleteAll()
    }
}