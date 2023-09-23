/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package com.ppfcbot.server.content.contentmanagement

import org.koin.dsl.module

val contentManagementModule = module {
    single {
        ContentManager(get())
    }

    single<ContentRepository> {
        ContentRepositoryImpl(get())
    }
}