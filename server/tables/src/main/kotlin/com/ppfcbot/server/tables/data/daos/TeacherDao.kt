/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package com.ppfcbot.server.tables.data.daos

import com.ppfcbot.server.tables.data.models.Teacher

internal interface TeacherDao : TablesEntityDao<Teacher> {

    fun insert(
        firstName: String,
        lastName: String,
        middleName: String,
        disciplineId: Long,
        isHeadTeacher: Boolean
    ): Long

    fun update(
        id: Long,
        firstName: String,
        lastName: String,
        middleName: String,
        disciplineId: Long,
        isHeadTeacher: Boolean
    )

    fun getAll(
        id: Long? = null,
        offset: Long? = null,
        limit: Long? = null,
        searchQuery: String? = null,
        firstName: String? = null,
        lastName: String? = null,
        disciplineId: Long? = null,
        disciplineName: String? = null
    ): List<Teacher>

    fun getWhereId(id: Long): Teacher?
}