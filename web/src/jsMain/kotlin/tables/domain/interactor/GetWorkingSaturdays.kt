/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package tables.domain.interactor

import core.domain.Interactor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import tables.domain.model.WorkingSaturdays
import tables.domain.repository.WorkingSaturdaysRepository

class GetWorkingSaturdays(
    private val workingSaturdaysRepository: WorkingSaturdaysRepository
) : Interactor<Unit, WorkingSaturdays>() {

    override suspend fun doWork(params: Unit) = withContext(Dispatchers.Default) {
        return@withContext workingSaturdaysRepository.get()
    }
}