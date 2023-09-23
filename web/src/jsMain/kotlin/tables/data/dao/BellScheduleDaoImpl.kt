/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package tables.data.dao

import api.ApiClient
import com.ppfcbot.common.api.models.auxiliary.BellScheduleRequest
import com.ppfcbot.common.api.models.auxiliary.BellScheduleResponse
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

class BellScheduleDaoImpl(
    private val apiClient: ApiClient
) : BellScheduleDao {

    override suspend fun insert(bellScheduleRequest: BellScheduleRequest) {
        apiClient.client.post(PATH) {
            contentType(ContentType.Application.Json)
            setBody(bellScheduleRequest)
        }
    }

    override suspend fun get(): BellScheduleResponse {
        return apiClient.client.get(PATH).body()
    }

    override suspend fun delete() {
        apiClient.client.delete(PATH)
    }

    private companion object {
        const val PATH = "bellSchedule"
    }
}