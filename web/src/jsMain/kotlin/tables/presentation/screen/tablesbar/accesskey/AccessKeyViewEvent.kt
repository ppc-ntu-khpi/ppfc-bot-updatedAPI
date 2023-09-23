/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package tables.presentation.screen.tablesbar.accesskey

import coreui.util.UiMessage

sealed interface AccessKeyViewEvent {
    class Message(val message: UiMessage) : AccessKeyViewEvent
}