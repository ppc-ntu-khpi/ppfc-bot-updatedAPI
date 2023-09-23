/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package com.ppfcbot.server.content.contentmanagement

import com.ppfcbot.server.infrastructure.config.ConfigProvider
import java.io.File
import java.io.IOException

class ContentManager(
    configProvider: ConfigProvider
) {

    private val contentDirectory = configProvider.config.contentDirectory

    fun getFilePaths(): List<FilePath> {
        return try {
            val root = File(contentDirectory)

            root.walkTopDown().mapNotNull { file ->
                return@mapNotNull if (file.isFile) {
                    val relativePath = root.toPath().relativize(file.toPath())
                    FilePath(relativePath.toString())
                } else null
            }.toList()
        } catch (e: IOException) {
            throw FilesStructureException()
        }
    }

    fun getFileBytes(fileName: String): ByteArray {
        return try {
            File("$contentDirectory/$fileName").readBytes()
        } catch (e: IOException) {
            throw FileReadException()
        }
    }

    fun saveFiles(path: String, fileNameToBytes: Map<String, ByteArray>, removeOldContent: Boolean) {
        val directoryPath = "$contentDirectory/$path"

        try {
            if (removeOldContent) {
                File(directoryPath).deleteRecursively()
            }
            File(directoryPath).mkdirs()

            for ((fileName, fileBytes) in fileNameToBytes) {
                File("$directoryPath/$fileName").writeBytes(fileBytes)
            }
        } catch (e: IOException) {
            throw FileSaveException()
        }
    }
}