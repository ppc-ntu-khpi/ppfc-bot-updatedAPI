/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package tables.data.mapper

import com.ppfcbot.common.api.models.tables.CourseRequest
import com.ppfcbot.common.api.models.tables.CourseResponse
import tables.domain.model.Course
import tables.domain.model.Id

fun Course.toRequest() = CourseRequest(
    number = number
)

fun CourseResponse.toDomain() = Course(
    id = Id.Value(value = id),
    number = number
)