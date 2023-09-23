/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package com.ppfcbot.server.tables.api.routes

import com.ppfcbot.common.api.models.tables.ChangeRequest
import com.ppfcbot.server.tables.api.tablesResponseHandler
import com.ppfcbot.server.tables.api.util.toIdsList
import com.ppfcbot.server.tables.changesdocument.ChangesWordDocumentRepository
import com.ppfcbot.server.tables.data.repositories.ChangeRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.changeRouting() {
    val changeRepository: ChangeRepository by inject()
    val changesWordDocumentRepository: ChangesWordDocumentRepository by inject()

    route("/change") {
        get("/generateWordDocument") {
            val date = call.request.queryParameters["date"] ?: run {
                call.respond(status = HttpStatusCode.BadRequest, message = "Parameter named 'date' is not found.")
                return@get
            }

            call.tablesResponseHandler {
                changesWordDocumentRepository.generate(date)
            }
        }

        get {
            val response = changeRepository.getAll(
                offset = call.request.queryParameters["offset"]?.toLongOrNull(),
                limit = call.request.queryParameters["limit"]?.toLongOrNull(),
                date = call.request.queryParameters["date"],
                isNumerator = call.request.queryParameters["isNumerator"]?.toBooleanStrictOrNull(),
                groupId = call.request.queryParameters["groupId"]?.toLongOrNull(),
                groupNumber = call.request.queryParameters["groupNumber"]?.toLongOrNull(),
                teacherId = call.request.queryParameters["teacherId"]?.toLongOrNull()
            )
            call.respond(status = HttpStatusCode.OK, message = response)
        }

        post {
            val changeRequest = call.receive<ChangeRequest>()

            call.tablesResponseHandler {
                changeRepository.add(changeRequest)
            }
        }

        post("/multiple") {
            val changeRequests = call.receive<List<ChangeRequest>>()

            call.tablesResponseHandler {
                changeRepository.addMultiple(changeRequests)
            }
        }

        put("{id?}") {
            val id = call.parameters["id"]?.toLongOrNull() ?: run {
                call.respond(status = HttpStatusCode.BadRequest, message = "Path parameter named 'id' is not found.")
                return@put
            }

            val changeRequest = call.receive<ChangeRequest>()

            call.tablesResponseHandler {
                changeRepository.update(id, changeRequest)
            }
        }

        put("/multiple") {
            val changes = call.receive<Map<Long, ChangeRequest>>()

            call.tablesResponseHandler {
                changeRepository.updateMultiple(changes)
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
                    changeRepository.delete(id)
                }
            }
        }

        delete("/all") {
            call.tablesResponseHandler {
                changeRepository.deleteAll()
            }
        }
    }
}