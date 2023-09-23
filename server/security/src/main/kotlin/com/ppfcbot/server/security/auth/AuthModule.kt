/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package com.ppfcbot.server.security.auth

import aws.smithy.kotlin.runtime.auth.awscredentials.CredentialsProvider
import org.koin.dsl.module

internal val authModule = module {
    single<CredentialsProvider> {
        CognitoCredentialsProvider(get())
    }

    single<AuthProvider> {
        CognitoAuthProvider(get(), get())
    }

    single<AuthRepository> {
        AuthRepositoryImpl(get())
    }
}