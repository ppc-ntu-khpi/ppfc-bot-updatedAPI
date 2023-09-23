/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package tables.data.mapper

import com.ppfcbot.common.api.models.tables.ChangeRequest
import com.ppfcbot.common.api.models.tables.ChangeResponse
import infrastructure.extensions.dateFromString
import infrastructure.extensions.toISO8601DateString
import tables.domain.model.*
import kotlin.js.Date

fun Change.toRequest() = ChangeRequest(
    groupsIds = groups.map { it.id.value },
    classroomId = if(classroom == Classroom.Empty) null else classroom.id.value,
    teacherId = if(teacher == Teacher.Empty) null else teacher.id.value,
    subjectId = if(subject == Subject.Empty) null else subject.id.value,
    eventName = eventName,
    lessonNumber = lessonNumber?.toNumber(),
    dayNumber = dayNumber.toNumber() ,
    date = date.toISO8601DateString(),
    isNumerator = weekAlternation.isNumerator
)

fun ChangeResponse.toDomain() = Change(
    id = Id.Value(value = id),
    groups = groups.map { it.toDomain() },
    classroom = classroom?.toDomain() ?: Classroom.Empty,
    teacher = teacher?.toDomain() ?: Teacher.Empty,
    subject = subject?.toDomain() ?: Subject.Empty,
    eventName = eventName,
    lessonNumber = lessonNumber?.toLessonNumber(),
    dayNumber = dayNumber.toDayNumber(),
    date = Date.dateFromString(date) ?: Date(),
    weekAlternation = isNumerator.toWeekAlternation()
)