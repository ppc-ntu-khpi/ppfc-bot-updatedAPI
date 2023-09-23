/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package tables.domain.model

data class BellSchedule(
    val text: String = ""
) {
    companion object {
        val Empty = BellSchedule()
    }
}