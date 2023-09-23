/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package com.ppfcbot.server.tables.data.models

internal data class Subject(
    override val id: Long = -1L,
    val name: String = ""
) : AppEntity<Long>