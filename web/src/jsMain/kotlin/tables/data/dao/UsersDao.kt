/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package tables.data.dao

import com.ppfcbot.common.api.models.tables.UserResponse

interface UsersDao {
    suspend fun deleteUsers(ids: Set<Long>)

    suspend fun getUsers(
        limit: Long,
        offset: Long,
        searchQuery: String?
    ): List<UserResponse>
}