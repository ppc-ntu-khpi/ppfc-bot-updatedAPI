/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package com.ppfcbot.server.tables.data.dbsqldelight.daos

import com.ppfcbot.server.tables.data.daos.SubjectDao
import com.ppfcbot.server.tables.data.models.Subject
import com.ppfcbot.server.tables.database.TablesDatabase

internal class SqlDelightSubjectDao(
    override val db: TablesDatabase
) : SubjectDao, SqlDelightEntityDao<Subject> {

    override fun insert(entity: Subject): Long = insert(
        name = entity.name
    )

    override fun insert(name: String): Long {
        db.subjectQueries.insert(
            id = null,
            name = name
        )

        return db.subjectQueries.lastInsertRowId().executeAsOne()
    }

    override fun update(entity: Subject) = update(
        id = entity.id,
        name = entity.name
    )

    override fun update(id: Long, name: String) {
        db.subjectQueries.updateWhereId(
            name = name,
            id = id
        )
    }

    override fun delete(id: Long) {
        db.subjectQueries.deleteWhereId(id)
    }

    override fun getAll(
        offset: Long?,
        limit: Long?,
        searchQuery: String?,
        name: String?
    ): List<Subject> {
        return db.subjectQueries.selectWithParameters(
            offset = offset,
            limit = limit,
            searchQuery = searchQuery,
            name = name
        ).executeAsList().map {
            Subject(
                id = it.id,
                name = it.name
            )
        }
    }

    override fun getWhereId(id: Long): Subject? {
        val dto = db.subjectQueries.selectWhereId(id).executeAsOneOrNull() ?: return null

        return Subject(
            id = dto.id,
            name = dto.name
        )
    }
}