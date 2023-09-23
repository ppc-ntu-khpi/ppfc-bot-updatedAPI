/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package tables.data.dao

import api.ApiClient
import com.ppfcbot.common.api.models.auxiliary.WorkingSaturdaysRequest
import com.ppfcbot.common.api.models.auxiliary.WorkingSaturdaysResponse
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

class WorkingSaturdaysDaoImpl(
    private val apiClient: ApiClient
) : WorkingSaturdaysDao {

    override suspend fun insert(workingSaturdaysRequest: WorkingSaturdaysRequest) {
        apiClient.client.post(PATH) {
            contentType(ContentType.Application.Json)
            setBody(workingSaturdaysRequest)
        }
    }

    override suspend fun get(): WorkingSaturdaysResponse {
        return apiClient.client.get(PATH).body()
    }

    override suspend fun delete() {
        apiClient.client.delete(PATH)
    }

    private companion object {
        const val PATH = "workingSaturdays"
    }
}