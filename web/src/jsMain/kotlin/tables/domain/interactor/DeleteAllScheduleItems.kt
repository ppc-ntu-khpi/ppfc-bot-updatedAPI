/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package tables.domain.interactor

import core.domain.Interactor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import tables.domain.repository.ScheduleRepository

class DeleteAllScheduleItems(
    private val scheduleRepository: ScheduleRepository
) : Interactor<Unit, Unit>() {

    override suspend fun doWork(params: Unit): Unit = withContext(Dispatchers.Default) {
        scheduleRepository.deleteAllScheduleItems()
    }
}