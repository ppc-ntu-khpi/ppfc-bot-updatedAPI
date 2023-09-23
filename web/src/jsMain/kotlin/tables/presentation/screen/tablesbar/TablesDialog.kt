/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package tables.presentation.screen.tablesbar

sealed interface TablesDialog {
    data object ShowAccessKey : TablesDialog
    data object ShowBellSchedule : TablesDialog
    data object ShowWorkingSaturdays : TablesDialog
}