/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package com.ppfcbot.server.security.api.plugins

import com.auth0.jwk.JwkProviderBuilder
import com.ppfcbot.server.infrastructure.config.ConfigProvider
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.response.*
import org.koin.ktor.ext.get
import java.util.concurrent.TimeUnit

internal fun Application.configureSecurity() {
    val config = get<ConfigProvider>().config

    val jwtIssuer = config.jwtIssuer
    val jwtRealm = config.jwtRealm

    val jwkProvider = JwkProviderBuilder(jwtIssuer)
        .cached(1000, 60, TimeUnit.MINUTES)
        .build()

    install(Authentication) {
        jwt {
            realm = jwtRealm

            verifier(jwkProvider, jwtIssuer) {
                acceptLeeway(3)
            }

            validate { credential ->
                JWTPrincipal(credential.payload)
            }

            challenge { _, _ ->
                call.respond(status = HttpStatusCode.Unauthorized, message = "Token is not valid or expired.")
            }
        }
    }
}