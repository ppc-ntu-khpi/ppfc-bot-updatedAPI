/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package tables.data.dao

import com.ppfcbot.common.api.models.tables.ChangeRequest
import com.ppfcbot.common.api.models.tables.ChangeResponse
import tables.domain.model.File

interface ChangesDao {
    suspend fun saveChange(changeRequest: ChangeRequest)

    suspend fun saveChanges(changesRequests: List<ChangeRequest>)

    suspend fun updateChange(changeRequest: ChangeRequest, id: Long)

    suspend fun updateChanges(changesRequests: Map<Long, ChangeRequest>)

    suspend fun deleteChanges(ids: Set<Long>)

    suspend fun deleteAllChanges()

    /**
     * @param date Format: yyyy-mm-dd.
     */
    suspend fun getChanges(
        limit: Long,
        offset: Long,
        date: String?,
        isNumerator: Boolean?,
        groupId: Long?,
        teacherId: Long?
    ): List<ChangeResponse>

    /**
     * @param date Format: yyyy-mm-dd.
     */
    suspend fun exportChangesToDocument(date: String): File
}