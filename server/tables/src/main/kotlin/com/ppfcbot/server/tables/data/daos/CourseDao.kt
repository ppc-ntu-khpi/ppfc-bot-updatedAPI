/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package com.ppfcbot.server.tables.data.daos

import com.ppfcbot.server.tables.data.models.Course

internal interface CourseDao : TablesEntityDao<Course> {

    fun insert(
        number: Long
    ): Long

    fun update(
        id: Long,
        number: Long
    )

    fun getAll(
        offset: Long? = null,
        limit: Long? = null,
        searchQuery: String? = null,
        number: Long? = null
    ): List<Course>

    fun getWhereId(id: Long): Course?
}