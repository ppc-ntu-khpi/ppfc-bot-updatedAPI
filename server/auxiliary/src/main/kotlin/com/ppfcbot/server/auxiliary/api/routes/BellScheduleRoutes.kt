/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package com.ppfcbot.server.auxiliary.api.routes

import com.ppfcbot.common.api.models.auxiliary.BellScheduleRequest
import com.ppfcbot.server.auxiliary.api.auxiliaryResponseHandler
import com.ppfcbot.server.auxiliary.data.bellschedule.BellScheduleRepository
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.bellScheduleRouting() {
    val bellScheduleRepository: BellScheduleRepository by inject()

    route("bellSchedule") {
        get {
            call.auxiliaryResponseHandler {
                bellScheduleRepository.get()
            }
        }

        post {
            val bellScheduleRequest = call.receive<BellScheduleRequest>()

            call.auxiliaryResponseHandler {
                bellScheduleRepository.add(bellScheduleRequest)
            }
        }

        delete {
            call.auxiliaryResponseHandler {
                bellScheduleRepository.delete()
            }
        }
    }
}