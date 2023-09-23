/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package tables.data.dao

import com.ppfcbot.common.api.models.tables.SubjectRequest
import com.ppfcbot.common.api.models.tables.SubjectResponse

interface SubjectsDao {
    suspend fun saveSubject(subjectRequest: SubjectRequest)

    suspend fun updateSubject(subjectRequest: SubjectRequest, id: Long)

    suspend fun deleteSubjects(ids: Set<Long>)

    suspend fun getSubjects(
        limit: Long,
        offset: Long,
        searchQuery: String?
    ): List<SubjectResponse>
}