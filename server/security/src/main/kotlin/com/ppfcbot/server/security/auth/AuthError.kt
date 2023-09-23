/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package com.ppfcbot.server.security.auth

internal sealed class AuthError(val message: String) {
    class NotAuthorized(message: String) : AuthError("Not authorized: $message")
    class InternalError(message: String? = null) : AuthError("Internal error: $message")
}
