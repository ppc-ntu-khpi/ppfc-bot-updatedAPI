/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package com.ppfcbot.server.content.contentmanagement

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ContentRepositoryImpl(
    private val contentManager: ContentManager
) : ContentRepository {

    override suspend fun getFileLinksHtml(): String = withContext(Dispatchers.IO) {
        contentManager.getFilePaths().map { filePath ->
            "<a href='/${filePath.value}'>${filePath.value}</a>"
        }.joinToString("<br>")
    }

    override suspend fun getFileBytes(fileName: String): ByteArray = withContext(Dispatchers.IO) {
        contentManager.getFileBytes(fileName)
    }

    override suspend fun saveFiles(
        path: String,
        fileNameToBytes: Map<String, ByteArray>,
        removeOldContent: Boolean
    ) = withContext(Dispatchers.IO) {
        contentManager.saveFiles(path, fileNameToBytes, removeOldContent)
    }
}