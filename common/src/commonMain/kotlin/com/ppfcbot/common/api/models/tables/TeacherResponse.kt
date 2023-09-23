/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package com.ppfcbot.common.api.models.tables

import kotlinx.serialization.Serializable

@Serializable
data class TeacherResponse(
    val id: Long,
    val firstName: String,
    val lastName: String,
    val middleName: String,
    val discipline: DisciplineResponse,
    val isHeadTeacher: Boolean
)