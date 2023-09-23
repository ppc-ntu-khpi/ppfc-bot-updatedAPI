/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package com.ppfcbot.server.tables.data.models

internal data class Teacher(
    override val id: Long = -1L,
    val firstName: String = "",
    val lastName: String = "",
    val middleName: String = "",
    val discipline: Discipline = Discipline(),
    val isHeadTeacher: Boolean = false
) : AppEntity<Long>