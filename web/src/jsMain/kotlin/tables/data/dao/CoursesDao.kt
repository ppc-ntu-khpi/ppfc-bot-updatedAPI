/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package tables.data.dao

import com.ppfcbot.common.api.models.tables.CourseRequest
import com.ppfcbot.common.api.models.tables.CourseResponse

interface CoursesDao {
    suspend fun saveCourse(courseRequest: CourseRequest)

    suspend fun updateCourse(courseRequest: CourseRequest, id: Long)

    suspend fun deleteCourse(ids: Set<Long>)

    suspend fun getCourses(
        limit: Long,
        offset: Long,
        searchQuery: String?
    ): List<CourseResponse>
}