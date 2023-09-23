/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package com.ppfcbot.server.auxiliary.data

import com.ppfcbot.server.auxiliary.data.bellschedule.bellScheduleModule
import com.ppfcbot.server.auxiliary.data.workingsaturdays.workingSaturdaysModule
import org.koin.dsl.module

internal val dataModule = module {
    single {
        AuxiliaryDatabaseConfigurator(get()).instance
    }

    includes(
        bellScheduleModule,
        workingSaturdaysModule
    )
}