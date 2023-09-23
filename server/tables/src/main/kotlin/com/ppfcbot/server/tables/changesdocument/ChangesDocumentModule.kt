/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package com.ppfcbot.server.tables.changesdocument

import org.koin.dsl.module

internal val changesDocumentModule = module {
    single {
        ChangesWordDocumentGenerator()
    }

    single<ChangesWordDocumentRepository> {
        ChangesWordDocumentRepositoryImpl(get(), get())
    }
}