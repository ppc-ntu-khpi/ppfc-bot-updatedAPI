/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package tables.data.dao

import com.ppfcbot.common.api.models.auxiliary.BellScheduleRequest
import com.ppfcbot.common.api.models.auxiliary.BellScheduleResponse

interface BellScheduleDao {
    suspend fun insert(bellScheduleRequest: BellScheduleRequest)
    suspend fun get(): BellScheduleResponse
    suspend fun delete()
}