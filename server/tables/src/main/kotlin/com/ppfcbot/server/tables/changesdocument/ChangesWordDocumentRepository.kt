/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package com.ppfcbot.server.tables.changesdocument

import com.ppfcbot.common.api.models.tables.ChangesDocumentResponse

internal interface ChangesWordDocumentRepository {
    fun generate(date: String): ChangesDocumentResponse
}