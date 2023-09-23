/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package com.ppfcbot.server.tables.data.models

internal data class User(
    override val id: Long = -1L,
    val group: Group? = null,
    val teacher: Teacher? = null,
    val isGroup: Boolean = false
) : AppEntity<Long>