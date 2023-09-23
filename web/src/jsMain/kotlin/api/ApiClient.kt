/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package api

import com.ppfc_bot.web.web.BuildConfig
import core.domain.NetworkException
import core.domain.NotFoundException
import core.domain.TimeoutException
import core.domain.UnexpectedErrorException
import core.infrastructure.Logger
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.js.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.http.*
import io.ktor.serialization.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json

class ApiClient {

    private val tag = ApiClient::class.simpleName.toString()

    @OptIn(ExperimentalSerializationApi::class)
    var client = HttpClient(Js) {
        expectSuccess = true

        install(HttpRequestRetry) {
            retryOnServerErrors(maxRetries = 3)
            exponentialDelay()
        }

        HttpResponseValidator {
            handleResponseExceptionWithRequest { cause, request ->
                val exception = when {
                    cause is RedirectResponseException -> UnexpectedErrorException()
                    cause is ClientRequestException -> {
                        if (cause.response.status == HttpStatusCode.NotFound) {
                            NotFoundException()
                        } else {
                            UnexpectedErrorException()
                        }
                    }
                    cause is ServerResponseException-> UnexpectedErrorException()
                    cause is JsonConvertException -> UnexpectedErrorException()
                    cause is NoTransformationFoundException -> UnexpectedErrorException()
                    cause is TimeoutCancellationException -> TimeoutException()
                    cause is CancellationException -> null
                    else -> NetworkException()
                } ?: return@handleResponseExceptionWithRequest

                val call = try {
                    request.call
                } catch (_: Exception) {
                    null
                }

                val message = """
                    Request failed.
                    Url: ${request.url}.
                    Method: ${request.method.value}.
                    Status code: ${call?.response}.
                    Cause: $cause.
                """.trimIndent().trim()

                Logger.error(tag = tag, message = message)

                throw exception
            }
        }

        install(ContentNegotiation) {
            json(
                Json {
                    prettyPrint = true
                    isLenient = true
                    explicitNulls = false
                }
            )
        }

        defaultRequest {
            url(BuildConfig.API_BASE_ADDRESS)
        }
    }
        private set

    fun config(block: HttpClientConfig<*>.() -> Unit) {
        client = client.config {
            block()
        }
    }
}