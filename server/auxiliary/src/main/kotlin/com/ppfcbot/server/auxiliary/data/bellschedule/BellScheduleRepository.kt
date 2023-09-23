/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package com.ppfcbot.server.auxiliary.data.bellschedule

import com.ppfcbot.common.api.models.auxiliary.BellScheduleRequest
import com.ppfcbot.common.api.models.auxiliary.BellScheduleResponse

internal interface BellScheduleRepository {
    suspend fun add(bellScheduleRequest: BellScheduleRequest)
    suspend fun get(): BellScheduleResponse?
    suspend fun delete()
}