/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package com.ppfcbot.server.tables.data.models

internal data class Group(
    override val id: Long = -1,
    val number: Long = 0L,
    val course: Course = Course()
) : AppEntity<Long>