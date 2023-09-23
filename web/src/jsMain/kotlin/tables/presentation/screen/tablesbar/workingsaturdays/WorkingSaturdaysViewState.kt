/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package tables.presentation.screen.tablesbar.workingsaturdays

import tables.domain.model.WorkingSaturdays

data class WorkingSaturdaysViewState(
    val isSaving: Boolean = false,
    val isLoading: Boolean = false,
    val errorLoading: Boolean = false,
    val workingSaturdays: WorkingSaturdays = WorkingSaturdays.Empty
) {
    companion object {
        val Empty = WorkingSaturdaysViewState()
    }
}