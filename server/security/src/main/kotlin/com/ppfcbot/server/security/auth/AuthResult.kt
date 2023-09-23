/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package com.ppfcbot.server.security.auth

internal sealed class AuthResult {
    class Success(val accessToken: String, val refreshToken: String) : AuthResult()
    class Failure(val error: AuthError) : AuthResult()
    class NewPasswordRequired(val session: String) : AuthResult()
}