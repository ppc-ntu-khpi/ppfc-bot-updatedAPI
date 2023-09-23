/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package com.ppfcbot.server.tables.data.dbsqldelight.daos

import com.ppfcbot.server.tables.data.daos.UserDao
import com.ppfcbot.server.tables.data.dbsqldelight.utils.toBoolean
import com.ppfcbot.server.tables.data.dbsqldelight.utils.toSqlBoolean
import com.ppfcbot.server.tables.data.models.*
import com.ppfcbot.server.tables.database.TablesDatabase

internal class SqlDelightUserDao(
    override val db: TablesDatabase
) : UserDao, SqlDelightEntityDao<User> {

    override fun insert(entity: User): Long = insert(
        groupId = entity.group?.id,
        teacherId = entity.teacher?.id,
        isGroup = entity.isGroup
    )

    override fun insert(groupId: Long?, teacherId: Long?, isGroup: Boolean): Long {
        db.userQueries.insert(
            id = null,
            groupId = groupId,
            teacherId = teacherId,
            isGroup = isGroup.toSqlBoolean()
        )

        return db.userQueries.lastInsertRowId().executeAsOne()
    }

    override fun update(entity: User) = update(
        groupId = entity.group?.id,
        teacherId = entity.teacher?.id,
        isGroup = entity.isGroup,
        id = entity.id
    )

    override fun update(id: Long, groupId: Long?, teacherId: Long?, isGroup: Boolean) {
        db.userQueries.updateWhereId(
            groupId = groupId,
            teacherId = teacherId,
            isGroup = isGroup.toSqlBoolean(),
            id = id
        )
    }

    override fun delete(id: Long) {
        db.userQueries.deleteWhereId(id)
    }

    override fun getAll(
        id: Long?,
        offset: Long?,
        limit: Long?,
        searchQuery: String?,
        isStudent: Boolean?
    ): List<User> {
        return db.userQueries.selectWithParameters(
            id = id,
            offset = offset,
            limit = limit,
            searchQuery = searchQuery,
            isGroup = isStudent?.toSqlBoolean()
        ).executeAsList().mapNotNull { entry ->
            val discipline = if (entry.disciplineId != null && entry.disciplineName != null) {
                Discipline(
                    id = entry.disciplineId,
                    name = entry.disciplineName
                )
            } else null

            val teacher = if (discipline != null &&
                entry.teacherId != null &&
                entry.teacherIsHeadTeacher != null &&
                entry.teacherFirstName != null &&
                entry.teacherLastName != null &&
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

            val course = if (entry.courseId != null && entry.courseNumber != null) {
                Course(
                    id = entry.courseId,
                    number = entry.courseNumber
                )
            } else null

            val group = if (course != null &&
                entry.groupId != null &&
                entry.groupNumber != null
            ) {
                Group(
                    id = entry.groupId,
                    number = entry.groupNumber,
                    course = course
                )
            } else null

            if (teacher == null && group == null) return@mapNotNull null

            User(
                id = entry.id,
                teacher = teacher,
                group = group,
                isGroup = teacher == null
            )
        }
    }
}