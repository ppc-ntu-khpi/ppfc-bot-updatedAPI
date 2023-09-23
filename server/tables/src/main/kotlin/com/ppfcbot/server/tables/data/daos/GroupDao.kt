/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package com.ppfcbot.server.tables.data.daos

import com.ppfcbot.server.tables.data.models.Group

internal interface GroupDao : TablesEntityDao<Group> {

    fun insert(
        number: Long,
        courseId: Long
    ): Long

    fun update(
        id: Long,
        number: Long,
        courseId: Long
    )

    fun getAll(
        id: Long? = null,
        offset: Long? = null,
        limit: Long? = null,
        searchQuery: String? = null,
        courseId: Long? = null,
        number: Long? = null
    ): List<Group>

    fun getWhereId(id: Long): Group?
}