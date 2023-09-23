/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package com.ppfcbot.server.tables.api.routes

import com.ppfcbot.common.api.models.tables.TeacherRequest
import com.ppfcbot.server.tables.api.tablesResponseHandler
import com.ppfcbot.server.tables.api.util.toIdsList
import com.ppfcbot.server.tables.data.repositories.TeacherRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.teacherRouting() {
    val teacherRepository: TeacherRepository by inject()

    route("/teacher") {
        get {
            call.tablesResponseHandler {
                teacherRepository.getAll(
                    offset = call.request.queryParameters["offset"]?.toLongOrNull(),
                    limit = call.request.queryParameters["limit"]?.toLongOrNull(),
                    searchQuery = call.request.queryParameters["query"],
                    disciplineId = call.request.queryParameters["disciplineId"]?.toLongOrNull(),
                    disciplineName = call.request.queryParameters["disciplineName"]
                )
            }
        }

        get("{id?}") {
            val id = call.parameters["id"]?.toLongOrNull() ?: run {
                call.respond(status = HttpStatusCode.BadRequest, message = "Path parameter named 'id' is not found.")
                return@get
            }

            call.tablesResponseHandler {
                teacherRepository.getAll(id = id).firstOrNull()
            }
        }

        get("byFirstAndLastName/{firstAndLastName}") {
            val firstAndLastName = call.parameters["firstAndLastName"] ?: run {
                call.respond(
                    status = HttpStatusCode.BadRequest,
                    message = "Path parameter named 'firstAndLastName' is not found."
                )
                return@get
            }

            call.tablesResponseHandler {
                teacherRepository.getAll(
                    firstName = firstAndLastName.substringBefore(" "),
                    lastName = firstAndLastName.substringAfter(" ")
                ).firstOrNull()
            }
        }

        post {
            val teacherRequest = call.receive<TeacherRequest>()

            call.tablesResponseHandler {
                teacherRepository.add(teacherRequest)
            }
        }

        put("{id?}") {
            val id = call.parameters["id"]?.toLongOrNull() ?: run {
                call.respond(status = HttpStatusCode.BadRequest, message = "Path parameter named 'id' is not found.")
                return@put
            }

            val teacherRequest = call.receive<TeacherRequest>()

            call.tablesResponseHandler {
                teacherRepository.update(id, teacherRequest)
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
                    teacherRepository.delete(id)
                }
            }
        }
    }
}