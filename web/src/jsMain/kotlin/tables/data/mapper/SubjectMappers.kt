/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package tables.data.mapper

import com.ppfcbot.common.api.models.tables.SubjectRequest
import com.ppfcbot.common.api.models.tables.SubjectResponse
import tables.domain.model.Id
import tables.domain.model.Subject

fun Subject.toRequest() = SubjectRequest(
    name = name
)

fun SubjectResponse.toDomain() = Subject(
    id = Id.Value(value = id),
    name = name
)