/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package com.ppfcbot.server.infrastructure.config


import kotlin.time.Duration

data class Config(
    val awsAccessKeyId: String,
    val awsSecretAccessKey: String,
    val awsRegion: String,
    val awsUserPoolId: String,
    val awsClientId: String,
    val jwtIssuer: String,
    val jwtAudience: String,
    val jwtRealm: String,
    val accessKeyExpiration: Duration,
    val contentDirectory: String,
    val tablesDatabaseName: String,
    val auxiliaryDatabaseName: String
)
