/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package com.ppfcbot.server.security.api.routes

import com.ppfcbot.server.security.accesskey.AccessKeyRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.accessKeyRouting() {
    val accessKeyRepository: AccessKeyRepository by inject()

    route("/accessKey") {
        get("/generate") {
            call.respond(status = HttpStatusCode.OK, message = accessKeyRepository.getAccessKey())
        }

        get("/verify/{key?}") {
            val key = call.parameters["key"] ?: run {
                call.respond(status = HttpStatusCode.BadRequest, message = "Parameter named 'key' is not found.")
                return@get
            }

            call.respond(status = HttpStatusCode.OK, message = accessKeyRepository.verifyAccessKey(key = key))
        }
    }
}