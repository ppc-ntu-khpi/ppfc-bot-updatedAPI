/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package com.ppfcbot.server.tables.data.dbsqldelight.daos

import com.ppfcbot.server.tables.data.daos.GroupDao
import com.ppfcbot.server.tables.data.models.Course
import com.ppfcbot.server.tables.data.models.Group
import com.ppfcbot.server.tables.database.TablesDatabase

internal class SqlDelightGroupDao(
    override val db: TablesDatabase
) : GroupDao, SqlDelightEntityDao<Group> {

    override fun insert(entity: Group): Long = insert(
        number = entity.number,
        courseId = entity.course.id
    )

    override fun insert(number: Long, courseId: Long): Long {
        db.groupQueries.insert(
            id = null,
            number = number,
            courseId = courseId
        )

        return db.groupQueries.lastInsertRowId().executeAsOne()
    }

    override fun update(entity: Group) = update(
        id = entity.id,
        number = entity.number,
        courseId = entity.course.id
    )

    override fun update(id: Long, number: Long, courseId: Long) {
        db.groupQueries.updateWhereId(
            number = number,
            courseId = courseId,
            id = id
        )
    }

    override fun delete(id: Long) {
        db.groupQueries.deleteWhereId(id)
    }

    override fun getAll(
        id: Long?,
        offset: Long?,
        limit: Long?,
        searchQuery: String?,
        courseId: Long?,
        number: Long?
    ): List<Group> {
        return db.groupQueries.selectWithParameters(
            id = id,
            offset = offset,
            limit = limit,
            searchQuery = searchQuery,
            courseId = courseId,
            number = number
        ).executeAsList().mapNotNull {
            val course = if (it.courseId != null && it.courseNumber != null) {
                Course(
                    id = it.courseId,
                    number = it.courseNumber
                )
            } else return@mapNotNull null

            Group(
                id = it.id,
                number = it.number,
                course = course
            )
        }
    }

    override fun getWhereId(id: Long): Group? {
        val dto = db.groupQueries.selectWhereId(id).executeAsOneOrNull() ?: return null

        val course = if (dto.courseId != null && dto.courseNumber != null) {
            Course(
                id = dto.courseId,
                number = dto.courseNumber
            )
        } else return null

        return Group(
            id = dto.id,
            number = dto.number,
            course = course
        )
    }
}