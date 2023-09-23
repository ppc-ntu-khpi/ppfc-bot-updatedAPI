/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package com.ppfcbot.server.tables.data.dbsqldelight.daos

import com.ppfcbot.server.tables.data.daos.TeacherDao
import com.ppfcbot.server.tables.data.dbsqldelight.utils.toBoolean
import com.ppfcbot.server.tables.data.dbsqldelight.utils.toSqlBoolean
import com.ppfcbot.server.tables.data.models.Discipline
import com.ppfcbot.server.tables.data.models.Teacher
import com.ppfcbot.server.tables.database.TablesDatabase

internal class SqlDelightTeacherDao(
    override val db: TablesDatabase
) : TeacherDao, SqlDelightEntityDao<Teacher> {

    override fun insert(entity: Teacher): Long = insert(
        firstName = entity.firstName,
        lastName = entity.lastName,
        middleName = entity.middleName,
        disciplineId = entity.discipline.id,
        isHeadTeacher = entity.isHeadTeacher
    )

    override fun insert(
        firstName: String,
        lastName: String,
        middleName: String,
        disciplineId: Long,
        isHeadTeacher: Boolean
    ): Long {
        db.teacherQueries.insert(
            id = null,
            firstName = firstName,
            lastName = lastName,
            middleName = middleName,
            disciplineId = disciplineId,
            isHeadTeacher = isHeadTeacher.toSqlBoolean()
        )

        return db.teacherQueries.lastInsertRowId().executeAsOne()
    }

    override fun update(entity: Teacher) = update(
        id = entity.id,
        firstName = entity.firstName,
        lastName = entity.lastName,
        middleName = entity.middleName,
        disciplineId = entity.discipline.id,
        isHeadTeacher = entity.isHeadTeacher
    )

    override fun update(
        id: Long,
        firstName: String,
        lastName: String,
        middleName: String,
        disciplineId: Long,
        isHeadTeacher: Boolean
    ) {
        db.teacherQueries.updateWhereId(
            firstName = firstName,
            lastName = lastName,
            middleName = middleName,
            disciplineId = disciplineId,
            isHeadTeacher = isHeadTeacher.toSqlBoolean(),
            id = id
        )
    }

    override fun delete(id: Long) {
        db.teacherQueries.deleteWhereId(id)
    }

    override fun getAll(
        id: Long?,
        offset: Long?,
        limit: Long?,
        searchQuery: String?,
        firstName: String?,
        lastName: String?,
        disciplineId: Long?,
        disciplineName: String?
    ): List<Teacher> {
        return db.teacherQueries.selectWithParameters(
            id = id,
            offset = offset,
            limit = limit,
            searchQuery = searchQuery,
            firstName = firstName,
            lastName = lastName,
            disciplineId = disciplineId,
            disciplineName = disciplineName
        ).executeAsList().mapNotNull {
            val discipline = if (it.discipline_id != null && it.discipline_name != null) {
                Discipline(
                    id = it.discipline_id,
                    name = it.discipline_name
                )
            } else return@mapNotNull null

            Teacher(
                id = it.id,
                firstName = it.firstName,
                lastName = it.lastName,
                middleName = it.middleName,
                discipline = discipline,
                isHeadTeacher = it.isHeadTeacher.toBoolean()
            )
        }
    }

    override fun getWhereId(id: Long): Teacher? {
        val dto = db.teacherQueries.selectWhereId(id).executeAsOneOrNull() ?: return null

        val discipline = if (dto.discipline_id != null && dto.discipline_name != null) {
            Discipline(
                id = dto.discipline_id,
                name = dto.discipline_name
            )
        } else return null

        return Teacher(
            id = dto.id,
            firstName = dto.firstName,
            lastName = dto.lastName,
            middleName = dto.middleName,
            discipline = discipline,
            isHeadTeacher = dto.isHeadTeacher.toBoolean()
        )
    }
}