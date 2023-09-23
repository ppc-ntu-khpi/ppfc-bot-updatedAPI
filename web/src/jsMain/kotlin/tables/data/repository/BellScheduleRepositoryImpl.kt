/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package tables.data.repository

import core.domain.NotFoundException
import tables.data.dao.BellScheduleDao
import tables.data.mapper.toDomain
import tables.data.mapper.toRequest
import tables.domain.model.BellSchedule
import tables.domain.repository.BellScheduleRepository

class BellScheduleRepositoryImpl(
    private val bellScheduleDao: BellScheduleDao
) : BellScheduleRepository {

    override suspend fun save(bellSchedule: BellSchedule) {
        bellScheduleDao.insert(bellSchedule.toRequest())
    }

    override suspend fun get(): BellSchedule {
        return try {
            bellScheduleDao.get().toDomain()
        } catch (e: NotFoundException) {
            BellSchedule.Empty
        }
    }
}