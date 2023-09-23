/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package com.ppfcbot.server.tables.changesdocument

import com.ppfcbot.common.api.models.tables.ChangesDocumentResponse
import com.ppfcbot.server.tables.data.daos.ChangeDao
import com.ppfcbot.server.tables.data.repositories.toResponse

internal class ChangesWordDocumentRepositoryImpl(
    private val changesWordDocumentGenerator: ChangesWordDocumentGenerator,
    private val changeDao: ChangeDao
) : ChangesWordDocumentRepository {

    override fun generate(date: String): ChangesDocumentResponse {
        val changes = changeDao.getAll(date = date).map { it.toResponse() }
        val fileBytes = changesWordDocumentGenerator.generate(changes = changes)

        return if(fileBytes == null) {
            ChangesDocumentResponse(error = "NO_CHANGES")
        } else {
            ChangesDocumentResponse(
                fileName = "$date.docx",
                fileBytes = fileBytes
            )
        }
    }
}