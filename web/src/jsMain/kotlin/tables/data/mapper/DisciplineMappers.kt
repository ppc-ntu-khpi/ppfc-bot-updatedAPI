/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package tables.data.mapper

import com.ppfcbot.common.api.models.tables.DisciplineRequest
import com.ppfcbot.common.api.models.tables.DisciplineResponse
import tables.domain.model.Discipline
import tables.domain.model.Id

fun Discipline.toRequest() = DisciplineRequest(
    name = name
)

fun DisciplineResponse.toDomain() = Discipline(
    id = Id.Value(value = id),
    name = name
)