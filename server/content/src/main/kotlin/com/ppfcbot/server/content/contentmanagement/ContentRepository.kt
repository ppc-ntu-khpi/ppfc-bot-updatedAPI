/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package com.ppfcbot.server.content.contentmanagement

interface ContentRepository {
    suspend fun getFileLinksHtml(): String
    suspend fun getFileBytes(fileName: String): ByteArray
    suspend fun saveFiles(path: String, fileNameToBytes: Map<String, ByteArray>, removeOldContent: Boolean)
}