/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package com.ppfcbot.server.auxiliary.data.workingsaturdays

import org.koin.dsl.module

internal val workingSaturdaysModule = module {
    single<WorkingSaturdaysDao> {
        SqlDelightWorkingSaturdaysDao(get())
    }

    single<WorkingSaturdaysRepository> {
        WorkingSaturdaysRepositoryImpl(get())
    }
}