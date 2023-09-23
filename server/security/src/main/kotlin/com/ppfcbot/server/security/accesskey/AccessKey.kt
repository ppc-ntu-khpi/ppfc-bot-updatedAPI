/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package com.ppfcbot.server.security.accesskey

internal data class  AccessKey(
    val key: String,
    val expiresAt: Long
)