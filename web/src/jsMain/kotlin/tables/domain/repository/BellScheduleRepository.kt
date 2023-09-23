/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package tables.domain.repository

import tables.domain.model.BellSchedule

interface BellScheduleRepository {
    suspend fun save(bellSchedule: BellSchedule)
    suspend fun get(): BellSchedule
}