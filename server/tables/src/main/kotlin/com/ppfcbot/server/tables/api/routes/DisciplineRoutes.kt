/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package com.ppfcbot.server.tables.api.routes

import com.ppfcbot.common.api.models.tables.DisciplineRequest
import com.ppfcbot.server.tables.api.tablesResponseHandler
import com.ppfcbot.server.tables.api.util.toIdsList
import com.ppfcbot.server.tables.data.repositories.DisciplineRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.disciplineRouting() {
    val disciplineRepository: DisciplineRepository by inject()

    route("/discipline") {
        get {
            call.tablesResponseHandler {
                disciplineRepository.getAll(
                    offset = call.request.queryParameters["offset"]?.toLongOrNull(),
                    limit = call.request.queryParameters["limit"]?.toLongOrNull(),
                    searchQuery = call.request.queryParameters["query"]
                )
            }
        }

        post {
            val disciplineRequest = call.receive<DisciplineRequest>()

            call.tablesResponseHandler {
                disciplineRepository.add(disciplineRequest)
            }
        }

        put("{id?}") {
            val id = call.parameters["id"]?.toLongOrNull() ?: run {
                call.respond(status = HttpStatusCode.BadRequest, message = "Path parameter named 'id' is not found.")
                return@put
            }

            val disciplineRequest = call.receive<DisciplineRequest>()

            call.tablesResponseHandler {
                disciplineRepository.update(id, disciplineRequest)
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
                for(id in ids) {
                    disciplineRepository.delete(id)
                }
            }
        }
    }
}