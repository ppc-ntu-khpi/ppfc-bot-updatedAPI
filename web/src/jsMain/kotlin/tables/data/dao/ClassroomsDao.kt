/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package tables.data.dao

import com.ppfcbot.common.api.models.tables.ClassroomRequest
import com.ppfcbot.common.api.models.tables.ClassroomResponse

interface ClassroomsDao {
    suspend fun saveClassroom(classroomRequest: ClassroomRequest)

    suspend fun updateClassroom(classroomRequest: ClassroomRequest, id: Long)

    suspend fun deleteClassrooms(ids: Set<Long>)

    suspend fun getClassrooms(
        limit: Long,
        offset: Long,
        searchQuery: String?
    ): List<ClassroomResponse>
}