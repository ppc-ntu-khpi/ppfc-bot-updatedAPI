/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package com.ppfcbot.server.tables.data.dbsqldelight.daos

import com.ppfcbot.server.tables.data.daos.ClassroomDao
import com.ppfcbot.server.tables.data.models.Classroom
import com.ppfcbot.server.tables.database.TablesDatabase

internal class SqlDelightClassroomDao(
    override val db: TablesDatabase
) : ClassroomDao, SqlDelightEntityDao<Classroom> {

    override fun insert(entity: Classroom): Long = insert(
        name = entity.name
    )

    override fun insert(name: String): Long {
        db.classroomQueries.insert(
            id = null,
            name = name
        )

        return db.classroomQueries.lastInsertRowId().executeAsOne()
    }

    override fun update(entity: Classroom) = update(
        id = entity.id,
        name = entity.name
    )

    override fun update(id: Long, name: String) {
        db.classroomQueries.updateWhereId(
            name = name,
            id = id
        )
    }

    override fun delete(id: Long) {
        db.classroomQueries.deleteWhereId(id)
    }

    override fun getAll(
        offset: Long?,
        limit: Long?,
        searchQuery: String?,
        name: String?
    ): List<Classroom> {
        return db.classroomQueries.selectWithParameters(
            offset = offset,
            limit = limit,
            searchQuery = searchQuery,
            name = name
        ).executeAsList().map {
            Classroom(
                id = it.id,
                name = it.name
            )
        }
    }

    override fun getWhereId(id: Long): Classroom? {
        val dto = db.classroomQueries.selectWhereId(id).executeAsOneOrNull() ?: return null

        return Classroom(
            id = dto.id,
            name = dto.name
        )
    }
}