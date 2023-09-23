/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package tables.domain.interactor

import core.domain.Interactor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import tables.domain.model.BellSchedule
import tables.domain.repository.BellScheduleRepository

class SaveBellSchedule(
    private val bellScheduleRepository: BellScheduleRepository
) : Interactor<SaveBellSchedule.Params, Unit>() {

    override suspend fun doWork(params: Params) = withContext(Dispatchers.Default) {
        return@withContext bellScheduleRepository.save(params.bellSchedule)
    }

    data class Params(val bellSchedule: BellSchedule)
}