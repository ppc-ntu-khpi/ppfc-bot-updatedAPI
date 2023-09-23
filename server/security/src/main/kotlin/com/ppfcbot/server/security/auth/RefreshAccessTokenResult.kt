/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package com.ppfcbot.server.security.auth

internal sealed class RefreshAccessTokenResult {
    class Success(val accessToken: String) : RefreshAccessTokenResult()
    class Failure(val error: AuthError) : RefreshAccessTokenResult()
}