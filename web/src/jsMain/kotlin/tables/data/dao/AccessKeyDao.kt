/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package tables.data.dao

import com.ppfcbot.common.api.models.tables.AccessKeyResponse

interface AccessKeyDao {
    suspend fun generateKey(): AccessKeyResponse
}