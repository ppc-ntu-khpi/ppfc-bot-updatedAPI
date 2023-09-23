/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package com.ppfcbot.server.tables.data.dbsqldelight.daos

import com.ppfcbot.server.tables.data.daos.DisciplineDao
import com.ppfcbot.server.tables.data.models.Discipline
import com.ppfcbot.server.tables.database.TablesDatabase

internal class SqlDelightDisciplineDao(
    override val db: TablesDatabase
) : DisciplineDao, SqlDelightEntityDao<Discipline> {

    override fun insert(entity: Discipline): Long = insert(
        name = entity.name
    )

    override fun insert(name: String): Long {
        db.disciplineQueries.insert(
            id = null,
            name = name
        )

        return db.disciplineQueries.lastInsertRowId().executeAsOne()
    }

    override fun update(entity: Discipline) = update(
        id = entity.id,
        name = entity.name
    )

    override fun update(id: Long, name: String) {
        db.disciplineQueries.updateWhereId(
            name = name,
            id = id
        )
    }

    override fun delete(id: Long) {
        db.disciplineQueries.deleteWhereId(id)
    }

    override fun getAll(
        offset: Long?,
        limit: Long?,
        searchQuery: String?,
        name: String?
    ): List<Discipline> {
        return db.disciplineQueries.selectWithParameters(
            offset = offset,
            limit = limit,
            searchQuery = searchQuery,
            name = name
        ).executeAsList().map {
            Discipline(
                id = it.id,
                name = it.name
            )
        }
    }

    override fun getWhereId(id: Long): Discipline? {
        val dto = db.disciplineQueries.selectWhereId(id).executeAsOneOrNull() ?: return null

        return Discipline(
            id = dto.id,
            name = dto.name
        )
    }
}