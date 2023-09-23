/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package com.ppfcbot.server.auxiliary.data.workingsaturdays

import com.ppfcbot.common.api.models.auxiliary.WorkingSaturdaysRequest
import com.ppfcbot.common.api.models.auxiliary.WorkingSaturdaysResponse

internal interface WorkingSaturdaysRepository {
    suspend fun add(workingSaturdaysRequest: WorkingSaturdaysRequest)
    suspend fun get(): WorkingSaturdaysResponse?
    suspend fun delete()
}