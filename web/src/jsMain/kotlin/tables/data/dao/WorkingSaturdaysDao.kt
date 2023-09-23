/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package tables.data.dao

import com.ppfcbot.common.api.models.auxiliary.WorkingSaturdaysRequest
import com.ppfcbot.common.api.models.auxiliary.WorkingSaturdaysResponse

interface WorkingSaturdaysDao {
    suspend fun insert(workingSaturdaysRequest: WorkingSaturdaysRequest)
    suspend fun get(): WorkingSaturdaysResponse
    suspend fun delete()
}