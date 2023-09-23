/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package tables.data.mapper

import com.ppfcbot.common.api.models.tables.ClassroomRequest
import com.ppfcbot.common.api.models.tables.ClassroomResponse
import tables.domain.model.Classroom
import tables.domain.model.Id

fun Classroom.toRequest() = ClassroomRequest(
    name = name
)

fun ClassroomResponse.toDomain() = Classroom(
    id = Id.Value(value = id),
    name = name
)