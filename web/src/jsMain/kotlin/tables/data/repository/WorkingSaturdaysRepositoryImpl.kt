/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package tables.data.repository

import core.domain.NotFoundException
import tables.data.dao.WorkingSaturdaysDao
import tables.data.mapper.toDomain
import tables.data.mapper.toRequest
import tables.domain.model.WorkingSaturdays
import tables.domain.repository.WorkingSaturdaysRepository

class WorkingSaturdaysRepositoryImpl(
    private val workingSaturdaysDao: WorkingSaturdaysDao
) : WorkingSaturdaysRepository {

    override suspend fun save(workingSaturdays: WorkingSaturdays) {
        workingSaturdaysDao.insert(workingSaturdays.toRequest())
    }

    override suspend fun get(): WorkingSaturdays {
        return try {
            workingSaturdaysDao.get().toDomain()
        } catch (e: NotFoundException) {
            WorkingSaturdays.Empty
        }
    }
}