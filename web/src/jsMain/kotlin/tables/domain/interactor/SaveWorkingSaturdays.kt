/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package tables.domain.interactor

import core.domain.Interactor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import tables.domain.model.WorkingSaturdays
import tables.domain.repository.WorkingSaturdaysRepository

class SaveWorkingSaturdays(
    private val workingSaturdaysRepository: WorkingSaturdaysRepository
) : Interactor<SaveWorkingSaturdays.Params, Unit>() {

    override suspend fun doWork(params: Params) = withContext(Dispatchers.Default) {
        return@withContext workingSaturdaysRepository.save(params.workingSaturdays)
    }

    data class Params(val workingSaturdays: WorkingSaturdays)
}