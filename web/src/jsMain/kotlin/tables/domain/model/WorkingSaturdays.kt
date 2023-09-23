/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package tables.domain.model

data class WorkingSaturdays(
    val text: String = ""
) {
    companion object {
        val Empty = WorkingSaturdays()
    }
}