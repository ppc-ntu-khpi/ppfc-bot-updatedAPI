/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package com.ppfcbot.server.security.auth

import com.ppfcbot.common.api.models.auth.AuthResponse
import com.ppfcbot.common.api.models.auth.AuthResponseStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class AuthRepositoryImpl(
    private val authProvider: AuthProvider
) : AuthRepository {

    override suspend fun authenticate(
        username: String,
        password: String
    ): AuthResponse = withContext(Dispatchers.IO) {
        val result = authProvider.auth(
            username = username,
            password = password
        )

        return@withContext when (result) {
            is AuthResult.Success -> {
                AuthResponse(
                    status = AuthResponseStatus.SUCCESS,
                    accessToken = result.accessToken,
                    refreshToken = result.refreshToken
                )
            }

            is AuthResult.Failure -> {
                when (result.error) {
                    is AuthError.InternalError -> {
                        AuthResponse(
                            status = AuthResponseStatus.FAILURE,
                            error = result.error.message
                        )
                    }

                    is AuthError.NotAuthorized -> {
                        AuthResponse(
                            status = AuthResponseStatus.FAILURE,
                            error = result.error.message
                        )
                    }
                }
            }

            is AuthResult.NewPasswordRequired -> {
                AuthResponse(
                    status = AuthResponseStatus.NEW_PASSWORD_REQUIRED,
                    session = result.session
                )
            }
        }
    }

    override suspend fun passAauthNewPasswordRequiredChallenge(
        username: String,
        password: String,
        session: String
    ): AuthResponse = withContext(Dispatchers.IO) {
        val result = authProvider.authChallengeSetNewPassword(
            username = username,
            password = password,
            session = session
        )

        return@withContext when (result) {
            is AuthChallengeSetNewPasswordResult.Success -> {
                AuthResponse(
                    status = AuthResponseStatus.SUCCESS,
                    accessToken = result.accessToken,
                    refreshToken = result.refreshToken
                )
            }

            is AuthChallengeSetNewPasswordResult.Failure -> {
                when (result.error) {
                    is AuthError.InternalError -> {
                        AuthResponse(
                            status = AuthResponseStatus.FAILURE,
                            error = result.error.message
                        )
                    }

                    is AuthError.NotAuthorized -> {
                        AuthResponse(
                            status = AuthResponseStatus.FAILURE,
                            error = result.error.message
                        )
                    }
                }
            }
        }
    }

    override suspend fun refreshAccessToken(
        refreshToken: String
    ): AuthResponse = withContext(Dispatchers.IO) {
        val result = authProvider.refreshAccessToken(
            refreshToken = refreshToken
        )

        return@withContext when (result) {
            is RefreshAccessTokenResult.Success -> {
                AuthResponse(
                    status = AuthResponseStatus.SUCCESS,
                    accessToken = result.accessToken,
                    refreshToken = refreshToken
                )
            }

            is RefreshAccessTokenResult.Failure -> {
                when (result.error) {
                    is AuthError.InternalError -> {
                        AuthResponse(
                            status = AuthResponseStatus.FAILURE,
                            error = result.error.message
                        )
                    }

                    is AuthError.NotAuthorized -> {
                        AuthResponse(
                            status = AuthResponseStatus.FAILURE,
                            error = result.error.message
                        )
                    }
                }
            }
        }
    }
}