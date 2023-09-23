/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package com.ppfcbot.server.auxiliary.api

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*

suspend inline fun <reified T> ApplicationCall.auxiliaryResponseHandler(
    noinline onSuccess: ((T) -> T)? = null,
    result: () -> T
) {
    runCatching {
        val data = result()
        if (data == null) {
            respond(
                status = HttpStatusCode.NotFound,
                message = "Not found."
            )
        } else {
            val responseData = onSuccess?.invoke(data) ?: data
            respond(
                status = HttpStatusCode.OK,
                message = responseData
            )
        }
    }.onFailure { cause ->
        cause.printStackTrace()

        respond(
            status = HttpStatusCode.InternalServerError,
            message = "Error."
        )
    }
}