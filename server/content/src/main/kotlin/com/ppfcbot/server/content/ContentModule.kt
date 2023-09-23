/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package com.ppfcbot.server.content

import com.ppfcbot.server.content.contentmanagement.contentManagementModule
import org.koin.dsl.module

val contentModule = module {
    includes(contentManagementModule)
}