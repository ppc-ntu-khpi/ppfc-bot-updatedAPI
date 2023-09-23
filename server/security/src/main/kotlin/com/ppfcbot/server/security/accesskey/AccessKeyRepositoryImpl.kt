/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package com.ppfcbot.server.security.accesskey

import com.ppfcbot.common.api.models.tables.AccessKeyResponse
import java.util.*

internal class AccessKeyRepositoryImpl(
    private val accessKeyGenerator: AccessKeyGenerator
) : AccessKeyRepository {

    private val accessKeys = Collections.synchronizedSet(mutableSetOf<AccessKey>())

    override fun getAccessKey(): AccessKeyResponse {
        val accessKey = accessKeyGenerator.generateAccessKey()

        accessKeys.add(accessKey)

        return AccessKeyResponse(
            key = accessKey.key,
            expiresAt = accessKey.expiresAt
        )
    }

    override fun verifyAccessKey(key: String): Boolean {
        return accessKeys.any {
            it.key == key && it.expiresAt >= System.currentTimeMillis()
        }
    }
}