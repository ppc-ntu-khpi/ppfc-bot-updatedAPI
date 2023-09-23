/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package tables.domain.interactor

import core.domain.Interactor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import tables.domain.model.BellSchedule
import tables.domain.repository.BellScheduleRepository

class GetBellSchedule(
    private val bellScheduleRepository: BellScheduleRepository
) : Interactor<Unit, BellSchedule>() {

    override suspend fun doWork(params: Unit) = withContext(Dispatchers.Default) {
        return@withContext bellScheduleRepository.get()
    }
}