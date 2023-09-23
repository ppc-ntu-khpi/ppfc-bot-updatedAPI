/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package com.ppfcbot.server.security.api.routes

import com.ppfcbot.common.api.models.auth.AuthNewPasswordRequiredChallengeRequest
import com.ppfcbot.common.api.models.auth.AuthRequest
import com.ppfcbot.common.api.models.auth.RefreshAccessTokenRequest
import com.ppfcbot.server.security.auth.AuthRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

internal fun Route.authRouting() {
    val authRepository: AuthRepository by inject()

    post("/authenticate") {
        val authRequest = call.receive<AuthRequest>()

        val result = authRepository.authenticate(
            username = authRequest.username,
            password = authRequest.password
        )

        call.respond(status = HttpStatusCode.OK, message = result)
    }

    post("/authNewPasswordRequiredChallenge") {
        val challengeRequest = call.receive<AuthNewPasswordRequiredChallengeRequest>()

        val result = authRepository.passAauthNewPasswordRequiredChallenge(
            username = challengeRequest.username,
            password = challengeRequest.password,
            session = challengeRequest.session
        )

        call.respond(status = HttpStatusCode.OK, message = result)
    }

    post("/refreshAccessToken") {
        val refreshAccessTokenRequest = call.receive<RefreshAccessTokenRequest>()

        val result = authRepository.refreshAccessToken(
            refreshToken = refreshAccessTokenRequest.refreshToken
        )

        call.respond(status = HttpStatusCode.OK, message = result)
    }
}