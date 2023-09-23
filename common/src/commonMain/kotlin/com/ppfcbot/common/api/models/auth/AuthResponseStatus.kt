/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package com.ppfcbot.common.api.models.auth

import kotlinx.serialization.Serializable

@Serializable
enum class AuthResponseStatus {
    SUCCESS,
    FAILURE,
    NEW_PASSWORD_REQUIRED
}

