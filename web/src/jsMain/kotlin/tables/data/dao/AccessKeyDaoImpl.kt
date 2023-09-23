/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package tables.data.dao

import api.ApiClient
import com.ppfcbot.common.api.models.tables.AccessKeyResponse
import io.ktor.client.call.*
import io.ktor.client.request.*

class AccessKeyDaoImpl(
    private val apiClient: ApiClient
) : AccessKeyDao {

    override suspend fun generateKey(): AccessKeyResponse {
        return apiClient.client.get("$PATH/generate").body()
    }

    private companion object {
        const val PATH = "accessKey"
    }
}