/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package com.ppfcbot.server.security.accesskey

import org.koin.dsl.module

internal val accessKeyModule = module {
    single<AccessKeyGenerator> {
        AccessKeyGeneratorImpl(get())
    }

    single<AccessKeyRepository> {
        AccessKeyRepositoryImpl(get())
    }
}