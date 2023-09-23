/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package com.ppfcbot.server.tables.data

import com.ppfcbot.server.tables.changesdocument.changesDocumentModule
import org.koin.dsl.module

internal val dataModule = module {
    includes(
        persistenceModule,
        changesDocumentModule
    )
}