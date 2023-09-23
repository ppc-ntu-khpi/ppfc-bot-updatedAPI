/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package com.ppfcbot.server.tables.data.models

internal data class Change(
    override val id: Long = -1,
    val groups: List<Group> = emptyList(),
    val classroom: Classroom? = null,
    val teacher: Teacher? = null,
    val subject: Subject? = null,
    val eventName: String? = null,
    val isSubject: Boolean = false,
    val lessonNumber: Long? = null,
    val date: String = "",
    val isNumerator: Boolean = false,
    val dayNumber: Long = 0
) : AppEntity<Long>