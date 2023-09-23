/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package tables.data.mapper

import com.ppfcbot.common.api.models.auxiliary.BellScheduleRequest
import com.ppfcbot.common.api.models.auxiliary.BellScheduleResponse
import tables.domain.model.BellSchedule

fun BellSchedule.toRequest() = BellScheduleRequest(text)

fun BellScheduleResponse.toDomain() = BellSchedule(text)