/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package com.ppfcbot.server.auxiliary.api.routes

import com.ppfcbot.common.api.models.auxiliary.WorkingSaturdaysRequest
import com.ppfcbot.server.auxiliary.api.auxiliaryResponseHandler
import com.ppfcbot.server.auxiliary.data.workingsaturdays.WorkingSaturdaysRepository
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.workingSaturdaysRouting() {
    val workingSaturdaysRepository: WorkingSaturdaysRepository by inject()

    route("/workingSaturdays") {
        get {
            call.auxiliaryResponseHandler {
                workingSaturdaysRepository.get()
            }
        }

        post {
            val workingSaturdaysRequest = call.receive<WorkingSaturdaysRequest>()

            call.auxiliaryResponseHandler {
                workingSaturdaysRepository.add(workingSaturdaysRequest)
            }
        }

        delete {
            call.auxiliaryResponseHandler {
                workingSaturdaysRepository.delete()
            }
        }
    }
}