/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package com.ppfcbot.server.tables.api

import com.ppfcbot.server.tables.api.routes.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.routing.*

fun Application.configureTablesFeature() {
    routing {
        route("/api") {
            authenticate {
                changeRouting()
                classroomRouting()
                courseRouting()
                disciplineRouting()
                groupRouting()
                scheduleRouting()
                subjectRouting()
                teacherRouting()
                userRouting()
            }
        }
    }
}