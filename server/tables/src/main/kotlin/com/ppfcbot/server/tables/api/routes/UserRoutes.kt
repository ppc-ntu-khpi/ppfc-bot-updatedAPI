/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package com.ppfcbot.server.tables.api.routes

import com.ppfcbot.common.api.models.tables.UserRequest
import com.ppfcbot.server.tables.api.tablesResponseHandler
import com.ppfcbot.server.tables.api.util.toIdsList
import com.ppfcbot.server.tables.data.repositories.UserRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.userRouting() {
    val userRepository: UserRepository by inject()

    route("/user") {
        get {
            call.tablesResponseHandler {
                userRepository.getAll(
                    offset = call.request.queryParameters["offset"]?.toLongOrNull(),
                    limit = call.request.queryParameters["limit"]?.toLongOrNull(),
                    searchQuery = call.request.queryParameters["query"],
                    isStudent = call.request.queryParameters["isStudent"]?.toBooleanStrictOrNull()
                )
            }
        }

        get("{id?}") {
            val id = call.parameters["id"]?.toLongOrNull() ?: run {
                call.respond(status = HttpStatusCode.BadRequest, message = "Path parameter named 'id' is not found.")
                return@get
            }

            call.tablesResponseHandler {
                userRepository.getAll(id = id).firstOrNull()
            }
        }

        post {
            val userRequest = call.receive<UserRequest>()

            call.tablesResponseHandler {
                userRepository.add(userRequest)
            }
        }

        delete("{ids?}") {
            val idsText = call.parameters["ids"] ?: run {
                call.respond(status = HttpStatusCode.BadRequest, message = "Path parameter named 'ids' is not found.")
                return@delete
            }

            val ids = idsText.toIdsList() ?: run {
                call.respond(status = HttpStatusCode.BadRequest, message = "Argument format error.")
                return@delete
            }

            call.tablesResponseHandler {
                for (id in ids) {
                    userRepository.delete(id)
                }
            }
        }
    }
}