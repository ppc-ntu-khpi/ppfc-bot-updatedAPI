/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package tables.data.mapper

import com.ppfcbot.common.api.models.tables.TeacherRequest
import com.ppfcbot.common.api.models.tables.TeacherResponse
import tables.domain.model.Id
import tables.domain.model.Teacher

fun Teacher.toRequest() = TeacherRequest(
    firstName = firstName,
    lastName = lastName,
    middleName = middleName,
    disciplineId = discipline.id.value,
    isHeadTeacher = isHeadTeacher
)

fun TeacherResponse.toDomain() = Teacher(
    id = Id.Value(value = id),
    firstName = firstName,
    lastName = lastName,
    middleName = middleName,
    discipline = discipline.toDomain(),
    isHeadTeacher = isHeadTeacher
)