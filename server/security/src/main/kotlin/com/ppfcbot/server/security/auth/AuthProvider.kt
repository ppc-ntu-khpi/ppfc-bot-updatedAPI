/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package com.ppfcbot.server.security.auth

internal interface AuthProvider {
    suspend fun auth(username: String, password: String): AuthResult

    suspend fun authChallengeSetNewPassword(
        username: String,
        password: String,
        session: String
    ): AuthChallengeSetNewPasswordResult

    suspend fun refreshAccessToken(refreshToken: String): RefreshAccessTokenResult
}