/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package com.ppfcbot.server.security.accesskey

import com.ppfcbot.common.api.models.tables.AccessKeyResponse

internal interface AccessKeyRepository {
    fun getAccessKey(): AccessKeyResponse
    fun verifyAccessKey(key: String): Boolean
}