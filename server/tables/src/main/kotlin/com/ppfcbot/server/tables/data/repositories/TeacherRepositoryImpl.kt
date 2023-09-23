/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package com.ppfcbot.server.tables.data.repositories

import com.ppfcbot.common.api.models.tables.TeacherRequest
import com.ppfcbot.common.api.models.tables.TeacherResponse
import com.ppfcbot.server.tables.data.daos.TeacherDao
import com.ppfcbot.server.tables.data.models.Teacher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal fun Teacher.toResponse() = TeacherResponse(
    id = id,
    firstName = firstName,
    lastName = lastName,
    middleName = middleName,
    discipline = discipline.toResponse(),
    isHeadTeacher = isHeadTeacher
)

internal class TeacherRepositoryImpl(
    private val teacherDao: TeacherDao
) : TeacherRepository {

    override suspend fun add(teacherRequest: TeacherRequest): Unit = withContext(Dispatchers.IO) {
        teacherDao.insert(
            firstName = teacherRequest.firstName,
            lastName = teacherRequest.lastName,
            middleName = teacherRequest.middleName,
            disciplineId = teacherRequest.disciplineId,
            isHeadTeacher = teacherRequest.isHeadTeacher
        )
    }

    override suspend fun getAll(
        id: Long?,
        offset: Long?,
        limit: Long?,
        searchQuery: String?,
        firstName: String?,
        lastName: String?,
        disciplineId: Long?,
        disciplineName: String?
    ): List<TeacherResponse> = withContext(Dispatchers.IO) {
        return@withContext teacherDao.getAll(
            id,
            offset,
            limit,
            searchQuery,
            firstName,
            lastName,
            disciplineId,
            disciplineName
        ).map { it.toResponse() }
    }

    override suspend fun update(
        id: Long,
        teacherRequest: TeacherRequest
    ) = withContext(Dispatchers.IO) {
        teacherDao.update(
            id = id,
            firstName = teacherRequest.firstName,
            lastName = teacherRequest.lastName,
            middleName = teacherRequest.middleName,
            disciplineId = teacherRequest.disciplineId,
            isHeadTeacher = teacherRequest.isHeadTeacher
        )
    }

    override suspend fun delete(id: Long) = withContext(Dispatchers.IO) {
        teacherDao.delete(id)
    }
}