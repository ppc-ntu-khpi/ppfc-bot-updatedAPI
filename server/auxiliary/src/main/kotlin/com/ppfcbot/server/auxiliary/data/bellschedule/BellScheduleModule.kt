/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package com.ppfcbot.server.auxiliary.data.bellschedule

import org.koin.dsl.module

internal val bellScheduleModule = module {
    single<BellScheduleDao> {
        SqlDelightBellScheduleDao(get())
    }

    single<BellScheduleRepository> {
        BellScheduleRepositoryImpl(get())
    }
}