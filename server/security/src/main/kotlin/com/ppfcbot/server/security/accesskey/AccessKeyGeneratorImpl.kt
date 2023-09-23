/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package com.ppfcbot.server.security.accesskey

import com.ppfcbot.server.infrastructure.config.ConfigProvider
import java.util.*
import kotlin.math.abs

internal class AccessKeyGeneratorImpl(
    private val configProvider: ConfigProvider
) : AccessKeyGenerator {

    override fun generateAccessKey(): AccessKey {
        val uuid = UUID.randomUUID().leastSignificantBits
        val key = (abs(uuid) % 9000) + 1000

        val expiresAt = System.currentTimeMillis() + configProvider.config.accessKeyExpiration.inWholeMilliseconds

        return AccessKey(
            key = key.toString(),
            expiresAt = expiresAt
        )
    }
}