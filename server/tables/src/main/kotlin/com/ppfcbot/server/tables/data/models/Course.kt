/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package com.ppfcbot.server.tables.data.models

internal data class Course(
    override val id: Long = -1,
    val number: Long = 0L
) : AppEntity<Long>