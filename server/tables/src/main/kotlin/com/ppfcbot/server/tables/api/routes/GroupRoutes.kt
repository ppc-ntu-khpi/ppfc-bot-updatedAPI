/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package com.ppfcbot.server.tables.api.routes

import com.ppfcbot.common.api.models.tables.GroupRequest
import com.ppfcbot.server.tables.api.tablesResponseHandler
import com.ppfcbot.server.tables.api.util.toIdsList
import com.ppfcbot.server.tables.data.repositories.GroupRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.groupRouting() {
    val groupRepository: GroupRepository by inject()

    route("/group") {
        get {
            call.tablesResponseHandler {
                groupRepository.getAll(
                    offset = call.request.queryParameters["offset"]?.toLongOrNull(),
                    limit = call.request.queryParameters["limit"]?.toLongOrNull(),
                    searchQuery = call.request.queryParameters["query"],
                    courseId = call.request.queryParameters["courseId"]?.toLongOrNull(),
                    courseNumber = call.request.queryParameters["courseNumber"]?.toLongOrNull()
                )
            }
        }

        get("{id?}") {
            val id = call.parameters["id"]?.toLongOrNull() ?: run {
                call.respond(status = HttpStatusCode.BadRequest, message = "Path parameter named 'id' is not found.")
                return@get
            }

            call.tablesResponseHandler {
                groupRepository.getAll(id = id).firstOrNull()
            }
        }

        get("byNumber/{number}") {
            val number = call.parameters["number"]?.toLongOrNull() ?: run {
                call.respond(status = HttpStatusCode.BadRequest, message = "Path parameter named 'id' is not found.")
                return@get
            }

            call.tablesResponseHandler {
                groupRepository.getAll(courseNumber = number).first()
            }
        }

        post {
            val groupRequest = call.receive<GroupRequest>()

            call.tablesResponseHandler {
                groupRepository.add(groupRequest)
            }
        }

        put("{id?}") {
            val id = call.parameters["id"]?.toLongOrNull() ?: run {
                call.respond(status = HttpStatusCode.BadRequest, message = "Path parameter named 'id' is not found.")
                return@put
            }

            val groupRequest = call.receive<GroupRequest>()

            call.tablesResponseHandler {
                groupRepository.update(id, groupRequest)
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
                    groupRepository.delete(id)
                }
            }
        }
    }
}