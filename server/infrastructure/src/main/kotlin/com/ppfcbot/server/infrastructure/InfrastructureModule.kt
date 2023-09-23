/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package com.ppfcbot.server.infrastructure

import com.ppfcbot.server.infrastructure.config.configModule
import org.koin.dsl.module

val infrastructureModule = module {
    includes(configModule)
}