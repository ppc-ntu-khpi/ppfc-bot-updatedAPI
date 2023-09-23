/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package com.ppfcbot.server.api

import com.ppfcbot.server.api.plugins.configureCors
import com.ppfcbot.server.api.plugins.configureDependencyInjection
import com.ppfcbot.server.api.plugins.configureSerialization
import com.ppfcbot.server.auxiliary.api.configureAuxiliaryFeature
import com.ppfcbot.server.content.api.configureContentFeature
import com.ppfcbot.server.security.api.configureSecurityFeature
import com.ppfcbot.server.tables.api.configureTablesFeature
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureCors()
    configureDependencyInjection()
    configureSerialization()

    configureSecurityFeature()
    configureTablesFeature()
    configureContentFeature()
    configureAuxiliaryFeature()
}