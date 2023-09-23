/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package com.ppfcbot.server.infrastructure.config

import java.util.*
import kotlin.time.Duration.Companion.seconds

internal class FileConfigProvider : ConfigProvider {

    override val config: Config by lazy {
        loadConfig()
    }

    private fun loadConfig(): Config {
        val configInputStream = javaClass.getResourceAsStream("/$CONFIG_NAME")
            ?: throw ConfigFileNotFoundException()

        val properties = Properties()
        properties.load(configInputStream)

        return Config(
            awsAccessKeyId = properties.getProperty("awsAccessKeyId"),
            awsSecretAccessKey = properties.getProperty("awsSecretAccessKey"),
            awsRegion = properties.getProperty("awsRegion"),
            awsUserPoolId = properties.getProperty("awsUserPoolId"),
            awsClientId = properties.getProperty("awsClientId"),
            jwtIssuer = properties.getProperty("jwtIssuer"),
            jwtAudience = properties.getProperty("jwtAudience"),
            jwtRealm = properties.getProperty("jwtRealm"),
            accessKeyExpiration = properties.getProperty("accessKeyExpirationSec").toLong().seconds,
            contentDirectory = properties.getProperty("contentDirectory"),
            tablesDatabaseName = properties.getProperty("tablesDatabaseName"),
            auxiliaryDatabaseName = properties.getProperty("auxiliaryDatabaseName")
        )
    }

    private companion object {
        const val CONFIG_NAME = "config.properties"
    }
}