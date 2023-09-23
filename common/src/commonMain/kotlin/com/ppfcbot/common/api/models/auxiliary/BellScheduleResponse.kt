/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package com.ppfcbot.common.api.models.auxiliary

import kotlinx.serialization.Serializable

@Serializable
data class BellScheduleResponse(
    val text: String
)