/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package com.ppfcbot.server.security.auth

import aws.smithy.kotlin.runtime.auth.awscredentials.Credentials
import aws.smithy.kotlin.runtime.auth.awscredentials.CredentialsProvider
import com.ppfcbot.server.infrastructure.config.ConfigProvider
import org.koin.core.component.KoinComponent

internal class CognitoCredentialsProvider(
    private val configProvider: ConfigProvider
) : CredentialsProvider, KoinComponent {

    override suspend fun getCredentials(): Credentials = Credentials(
        accessKeyId = configProvider.config.awsAccessKeyId,
        secretAccessKey = configProvider.config.awsSecretAccessKey
    )
}