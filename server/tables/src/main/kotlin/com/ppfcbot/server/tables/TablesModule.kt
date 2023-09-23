/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package com.ppfcbot.server.tables

import com.ppfcbot.server.tables.data.dataModule
import org.koin.dsl.module

val tablesModule = module {
    includes(
        dataModule
    )
}