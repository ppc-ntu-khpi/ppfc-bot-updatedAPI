/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package com.ppfcbot.server.tables.data.dbsqldelight.daos

import com.ppfcbot.server.tables.data.daos.CourseDao
import com.ppfcbot.server.tables.data.models.Course
import com.ppfcbot.server.tables.database.TablesDatabase

internal class SqlDelightCourseDao(
    override val db: TablesDatabase
) : CourseDao, SqlDelightEntityDao<Course> {

    override fun insert(entity: Course): Long = insert(
        number = entity.number
    )

    override fun insert(number: Long): Long {
        db.courseQueries.insert(
            id = null,
            number = number
        )

        return db.courseQueries.lastInsertRowId().executeAsOne()
    }

    override fun update(entity: Course) = update(
        id = entity.id,
        number = entity.number
    )

    override fun update(id: Long, number: Long) {
        db.courseQueries.updateWhereId(
            number = number,
            id = id
        )
    }

    override fun delete(id: Long) {
        db.courseQueries.deleteWhereId(id)
    }

    override fun getAll(
        offset: Long?,
        limit: Long?,
        searchQuery: String?,
        number: Long?
    ): List<Course> {
        return db.courseQueries.selectWithParameters(
            offset = offset,
            limit = limit,
            searchQuery = searchQuery,
            number = number
        ).executeAsList().map {
            Course(
                id = it.id,
                number = it.number
            )
        }
    }

    override fun getWhereId(id: Long): Course? {
        val dto = db.courseQueries.selectWhereId(id).executeAsOneOrNull() ?: return null

        return Course(
            id = dto.id,
            number = dto.number
        )
    }
}