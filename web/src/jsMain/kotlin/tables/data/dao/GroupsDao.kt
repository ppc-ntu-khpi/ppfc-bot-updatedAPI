/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package tables.data.dao

import com.ppfcbot.common.api.models.tables.GroupRequest
import com.ppfcbot.common.api.models.tables.GroupResponse

interface GroupsDao {
    suspend fun saveGroup(groupRequest: GroupRequest)

    suspend fun updateGroup(groupRequest: GroupRequest, id: Long)

    suspend fun deleteGroups(ids: Set<Long>)

    suspend fun getGroups(
        limit: Long,
        offset: Long,
        searchQuery: String?,
        courseId: Long?
    ): List<GroupResponse>
}