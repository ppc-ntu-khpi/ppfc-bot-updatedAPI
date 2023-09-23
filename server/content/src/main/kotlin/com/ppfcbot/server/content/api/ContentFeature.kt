/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package com.ppfcbot.server.content.api

import com.ppfcbot.server.content.api.routes.contentRouting
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureContentFeature() {
    routing {
        staticResources("/static", "/static")

        get("/") {
            call.respondRedirect("/static/WelcomeScreen.html")
        }

        contentRouting()
    }
}