/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package com.ppfcbot.server.api.plugins

import com.ppfcbot.server.api.mainModule
import io.ktor.server.application.*
import org.koin.ktor.plugin.Koin

fun Application.configureDependencyInjection() {
    install(Koin) {
        modules(mainModule)
    }
}