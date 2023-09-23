/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package tables.presentation.screen.tablesbar.accesskey

import tables.domain.model.AccessKey

data class AccessKeyViewState(
    val isLoading: Boolean = false,
    val errorLoading: Boolean = false,
    val accessKey: AccessKey = AccessKey.Empty
) {
    companion object {
        val Empty = AccessKeyViewState()
    }
}