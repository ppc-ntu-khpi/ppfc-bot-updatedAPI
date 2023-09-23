/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package com.ppfcbot.server.security.accesskey

internal interface AccessKeyGenerator {
    fun generateAccessKey(): AccessKey
}