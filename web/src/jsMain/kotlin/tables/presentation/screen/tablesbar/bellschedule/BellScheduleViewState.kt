/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package tables.presentation.screen.tablesbar.bellschedule

import tables.domain.model.BellSchedule

data class BellScheduleViewState(
    val isSaving: Boolean = false,
    val isLoading: Boolean = false,
    val errorLoading: Boolean = false,
    val bellSchedule: BellSchedule = BellSchedule.Empty
) {
    companion object {
        val Empty = BellScheduleViewState()
    }
}