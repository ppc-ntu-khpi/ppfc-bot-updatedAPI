/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package com.ppfcbot.server.security.auth

import com.ppfcbot.common.api.models.auth.AuthResponse

interface AuthRepository {
    suspend fun authenticate(username: String, password: String): AuthResponse
    suspend fun passAauthNewPasswordRequiredChallenge(
        username: String,
        password: String,
        session: String
    ): AuthResponse
    suspend fun refreshAccessToken(refreshToken: String): AuthResponse
}