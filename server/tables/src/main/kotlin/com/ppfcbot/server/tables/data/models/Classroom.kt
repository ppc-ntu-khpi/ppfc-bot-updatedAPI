/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package com.ppfcbot.server.tables.data.models

internal data class Classroom(
    override val id: Long = -1,
    val name: String = ""
) : AppEntity<Long>