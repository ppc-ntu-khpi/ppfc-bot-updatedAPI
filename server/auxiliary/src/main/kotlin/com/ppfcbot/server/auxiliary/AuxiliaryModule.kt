/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package com.ppfcbot.server.auxiliary

import com.ppfcbot.server.auxiliary.data.dataModule
import org.koin.dsl.module

val auxiliaryModule = module {
    includes(
        dataModule
    )
}