/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package com.ppfcbot.server.tables.api.util

fun String.toIdsList(): List<Long>? {
    if (!Regex("^[0-9,]+$").matches(this)) {
        return null
    }

    return this.split(',').map {
        it.toLongOrNull() ?: run {
            return null
        }
    }
}