/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package tables.domain.interactor

import core.domain.Interactor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import tables.domain.model.Id
import tables.domain.repository.TeachersRepository

class DeleteTeachers(
    private val teachersRepository: TeachersRepository
) : Interactor<DeleteTeachers.Params, Unit>() {

    override suspend fun doWork(params: Params): Unit = withContext(Dispatchers.Default) {
        teachersRepository.deleteTeachers(ids = params.ids)
    }

    data class Params(val ids: Set<Id>)
}