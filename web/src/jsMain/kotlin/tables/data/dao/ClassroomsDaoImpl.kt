/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package tables.data.dao

import api.ApiClient
import com.ppfcbot.common.api.models.tables.ClassroomRequest
import com.ppfcbot.common.api.models.tables.ClassroomResponse
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

class ClassroomsDaoImpl(
    private val apiClient: ApiClient
) : ClassroomsDao {

    override suspend fun saveClassroom(classroomRequest: ClassroomRequest) {
        apiClient.client.post(PATH) {
            contentType(ContentType.Application.Json)
            setBody(classroomRequest)
        }
    }

    override suspend fun updateClassroom(classroomRequest: ClassroomRequest, id: Long) {
        apiClient.client.put("$PATH/$id") {
            contentType(ContentType.Application.Json)
            setBody(classroomRequest)
        }
    }

    override suspend fun deleteClassrooms(ids: Set<Long>) {
        apiClient.client.delete("$PATH/${ids.joinToString(",")}")
    }

    override suspend fun getClassrooms(
        limit: Long,
        offset: Long,
        searchQuery: String?
    ): List<ClassroomResponse> {
        return apiClient.client.get(PATH) {
            if (limit > 0) parameter("limit", limit)
            if (offset > 0) parameter("offset", offset)
            if (searchQuery != null) parameter("query", searchQuery)
        }.body()
    }

    private companion object {
        const val PATH = "classroom"
    }
}