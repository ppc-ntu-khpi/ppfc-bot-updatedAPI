/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package tables.domain.repository

import tables.domain.model.WorkingSaturdays

interface WorkingSaturdaysRepository {
    suspend fun save(workingSaturdays: WorkingSaturdays)
    suspend fun get(): WorkingSaturdays
}