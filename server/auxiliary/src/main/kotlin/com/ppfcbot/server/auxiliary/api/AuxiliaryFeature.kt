/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package com.ppfcbot.server.auxiliary.api

import com.ppfcbot.server.auxiliary.api.routes.bellScheduleRouting
import com.ppfcbot.server.auxiliary.api.routes.workingSaturdaysRouting
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.routing.*

fun Application.configureAuxiliaryFeature() {
    routing {
        route("/api") {
            authenticate {
                bellScheduleRouting()
                workingSaturdaysRouting()
            }
        }
    }
}