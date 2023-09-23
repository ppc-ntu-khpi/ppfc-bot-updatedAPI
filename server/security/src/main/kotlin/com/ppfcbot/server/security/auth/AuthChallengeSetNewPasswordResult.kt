/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package com.ppfcbot.server.security.auth

internal sealed class AuthChallengeSetNewPasswordResult {
    class Success(val accessToken: String, val refreshToken: String) : AuthChallengeSetNewPasswordResult()
    class Failure(val error: AuthError) : AuthChallengeSetNewPasswordResult()
}
