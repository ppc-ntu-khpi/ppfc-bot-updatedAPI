/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package tables.data.mapper

import com.ppfcbot.common.api.models.tables.AccessKeyResponse
import tables.domain.model.AccessKey
import kotlin.js.Date

fun AccessKeyResponse.toDomain() = AccessKey(
    key = key,
    expiresAt = Date(expiresAt)
)