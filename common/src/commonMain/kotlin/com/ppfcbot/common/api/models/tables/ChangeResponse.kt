/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package com.ppfcbot.common.api.models.tables

import kotlinx.serialization.Serializable

@Serializable
data class ChangeResponse(
    val id: Long,
    val groups: List<GroupResponse>,
    val classroom: ClassroomResponse?,
    val teacher: TeacherResponse?,
    val subject: SubjectResponse?,
    val eventName: String?,
    val isSubject: Boolean,
    val lessonNumber: Long?,
    val dayNumber: Long,
    val date: String,
    val isNumerator: Boolean
)