/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package tables.domain.interactor

import core.domain.Interactor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import tables.domain.model.ScheduleItem
import tables.domain.repository.ScheduleRepository

class SaveScheduleItem(
    private val scheduleRepository: ScheduleRepository,
    private val processAndValidateScheduleItem: ProcessAndValidateScheduleItem
) : Interactor<SaveScheduleItem.Params, Unit>() {

    override suspend fun doWork(params: Params): Unit = withContext(Dispatchers.Default) {
        val scheduleItem = processAndValidateScheduleItem.executeSync(
            params = ProcessAndValidateScheduleItem.Params(scheduleItem = params.scheduleItem)
        )

        scheduleRepository.saveScheduleItem(scheduleItem = scheduleItem)
    }

    data class Params(val scheduleItem: ScheduleItem)
}