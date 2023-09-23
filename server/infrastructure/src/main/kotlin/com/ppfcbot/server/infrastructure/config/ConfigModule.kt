/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package com.ppfcbot.server.infrastructure.config

import org.koin.dsl.module

internal val configModule = module {
    single<ConfigProvider> {
        FileConfigProvider()
    }
}