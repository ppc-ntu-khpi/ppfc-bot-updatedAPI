/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package com.ppfcbot.common.api.models.tables

import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    val id: Long,
    val group: GroupResponse?,
    val teacher: TeacherResponse?,
    val isGroup: Boolean
)