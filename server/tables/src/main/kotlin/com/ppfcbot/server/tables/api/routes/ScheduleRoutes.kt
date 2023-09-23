/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package com.ppfcbot.server.tables.api.routes

import com.ppfcbot.common.api.models.tables.ScheduleRequest
import com.ppfcbot.server.tables.api.tablesResponseHandler
import com.ppfcbot.server.tables.api.util.toIdsList
import com.ppfcbot.server.tables.data.repositories.ScheduleRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.scheduleRouting() {
    val scheduleRepository: ScheduleRepository by inject()

    route("/schedule") {
        get {
            call.tablesResponseHandler {
                scheduleRepository.getAll(
                    offset = call.request.queryParameters["offset"]?.toLongOrNull(),
                    limit = call.request.queryParameters["limit"]?.toLongOrNull(),
                    dayNumber = call.request.queryParameters["dayNumber"]?.toLongOrNull(),
                    isNumerator = call.request.queryParameters["isNumerator"]?.toBooleanStrictOrNull(),
                    groupId = call.request.queryParameters["groupId"]?.toLongOrNull(),
                    groupNumber = call.request.queryParameters["groupNumber"]?.toLongOrNull(),
                    teacherId = call.request.queryParameters["teacherId"]?.toLongOrNull()
                )
            }
        }

        post {
            val scheduleRequest = call.receive<ScheduleRequest>()

            call.tablesResponseHandler {
                scheduleRepository.add(scheduleRequest)
            }
        }

        post("/multiple") {
            val scheduleRequest = call.receive<List<ScheduleRequest>>()

            call.tablesResponseHandler {
                scheduleRepository.addMultiple(scheduleRequest)
            }
        }

        put("{id?}") {
            val id = call.parameters["id"]?.toLongOrNull() ?: run {
                call.respond(status = HttpStatusCode.BadRequest, message = "Path parameter named 'id' is not found.")
                return@put
            }

            val scheduleRequest = call.receive<ScheduleRequest>()

            call.tablesResponseHandler {
                scheduleRepository.update(id, scheduleRequest)
            }
        }

        put("/multiple") {
            val scheduleRequest = call.receive<Map<Long, ScheduleRequest>>()

            call.tablesResponseHandler {
                scheduleRepository.updateMultiple(scheduleRequest)
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
                    scheduleRepository.delete(id)
                }
            }
        }

        delete("/all") {
            call.tablesResponseHandler {
                scheduleRepository.deleteAll()
            }
        }
    }
}