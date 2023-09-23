/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package com.ppfcbot.server.security.api

import com.ppfcbot.server.security.api.plugins.configureSecurity
import com.ppfcbot.server.security.api.routes.accessKeyRouting
import com.ppfcbot.server.security.api.routes.authRouting
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureSecurityFeature() {
    configureSecurity()

    routing {
        route("/api") {
            authRouting()
            accessKeyRouting()
        }
    }
}