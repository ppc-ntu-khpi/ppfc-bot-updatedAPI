/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package com.ppfcbot.server.api

import com.ppfcbot.server.auxiliary.auxiliaryModule
import com.ppfcbot.server.content.contentModule
import com.ppfcbot.server.infrastructure.infrastructureModule
import com.ppfcbot.server.security.securityModule
import com.ppfcbot.server.tables.tablesModule
import org.koin.dsl.module

internal val mainModule = module {
    includes(
        infrastructureModule,
        securityModule,
        tablesModule,
        contentModule,
        auxiliaryModule
    )
}