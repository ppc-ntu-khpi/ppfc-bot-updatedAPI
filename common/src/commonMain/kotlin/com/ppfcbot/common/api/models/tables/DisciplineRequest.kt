/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package com.ppfcbot.common.api.models.tables

import kotlinx.serialization.Serializable

@Serializable
data class DisciplineRequest(
    val name: String
)