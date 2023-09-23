/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package com.ppfcbot.server.tables.data.daos

import com.ppfcbot.server.tables.data.models.User

internal interface UserDao : TablesEntityDao<User> {

    fun insert(
        groupId: Long?,
        teacherId: Long?,
        isGroup: Boolean
    ): Long

    fun update(
        id: Long,
        groupId: Long?,
        teacherId: Long?,
        isGroup: Boolean
    )

    fun getAll(
        id: Long? = null,
        offset: Long? = null,
        limit: Long? = null,
        searchQuery: String? = null,
        isStudent: Boolean? = null
    ): List<User>
}