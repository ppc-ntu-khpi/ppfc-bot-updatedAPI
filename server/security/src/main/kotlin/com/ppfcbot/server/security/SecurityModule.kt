/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package com.ppfcbot.server.security

import com.ppfcbot.server.security.accesskey.accessKeyModule
import com.ppfcbot.server.security.auth.authModule
import org.koin.dsl.module

val securityModule = module {
    includes(
        accessKeyModule,
        authModule
    )
}