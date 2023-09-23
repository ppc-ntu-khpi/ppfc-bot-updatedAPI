/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package com.ppfcbot.server.tables.data.models

internal data class Schedule(
    override val id: Long = -1L,
    val group: Group = Group(),
    val classroom: Classroom = Classroom(),
    val teacher: Teacher = Teacher(),
    val subject: Subject? = null,
    val eventName: String? = null,
    val isSubject: Boolean = false,
    val lessonNumber: Long = 0L,
    val dayNumber: Long = 0L,
    val isNumerator: Boolean = false
) : AppEntity<Long>