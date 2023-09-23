/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package tables.data.mapper

import com.ppfcbot.common.api.models.tables.GroupRequest
import com.ppfcbot.common.api.models.tables.GroupResponse
import tables.domain.model.Group
import tables.domain.model.Id

fun Group.toRequest() = GroupRequest(
    number = number,
    courseId = course.number
)

fun GroupResponse.toDomain() = Group(
    id = Id.Value(value = id),
    number = number,
    course = course.toDomain()
)