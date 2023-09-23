/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package com.ppfcbot.server.tables.data.daos

import com.ppfcbot.server.tables.data.models.Classroom

internal interface ClassroomDao : TablesEntityDao<Classroom> {

    fun insert(
        name: String
    ): Long

    fun update(
        id: Long,
        name: String
    )

    fun getAll(
        offset: Long? = null,
        limit: Long? = null,
        searchQuery: String? = null,
        name: String? = null
    ): List<Classroom>

    fun getWhereId(id: Long): Classroom?
}