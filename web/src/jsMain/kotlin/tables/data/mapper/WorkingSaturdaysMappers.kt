/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package tables.data.mapper

import com.ppfcbot.common.api.models.auxiliary.WorkingSaturdaysRequest
import com.ppfcbot.common.api.models.auxiliary.WorkingSaturdaysResponse
import tables.domain.model.WorkingSaturdays

fun WorkingSaturdays.toRequest() = WorkingSaturdaysRequest(text)

fun WorkingSaturdaysResponse.toDomain() = WorkingSaturdays(text)