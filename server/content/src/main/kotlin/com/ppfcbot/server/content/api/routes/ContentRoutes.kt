/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package com.ppfcbot.server.content.api.routes

import com.ppfcbot.server.content.contentmanagement.ContentRepository
import com.ppfcbot.server.content.contentmanagement.FileReadException
import com.ppfcbot.server.content.contentmanagement.FileSaveException
import com.ppfcbot.server.content.contentmanagement.FilesStructureException
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.contentRouting() {
    val contentRepository: ContentRepository by inject()

    get("content") {
        try {
            val fileLinks = contentRepository.getFileLinksHtml()
            call.respondText(fileLinks, ContentType.Text.Html)
        } catch (_: FilesStructureException) {
            call.respond(HttpStatusCode.InternalServerError)
        }
    }

    get("/{file...}") {
        val file = call.parameters.getAll("file")?.joinToString(separator = "/")
            ?: return@get call.respond(HttpStatusCode.BadRequest)

        try {
            val fileBytes = contentRepository.getFileBytes(file)
            call.respondBytes(fileBytes)
        } catch (_: FileReadException) {
            call.respond(HttpStatusCode.NotFound)
        }
    }

    authenticate {
        post("/uploadFiles/{savePath...}") {
            val removeOldContent = call.request.queryParameters["clearOldFiles"]?.toBoolean()
                ?: return@post call.respond(HttpStatusCode.BadRequest)

            val savePath = call.parameters.getAll("savePath")?.joinToString(separator = "/")
                ?: return@post call.respond(HttpStatusCode.BadRequest)

            val fileNameToBytes = mutableMapOf<String, ByteArray>()

            try {
                call.receiveMultipart().forEachPart { part ->
                    if (part !is PartData.FileItem) return@forEachPart

                    val fileName = part.originalFileName ?: return@forEachPart
                    val fileBytes = part.streamProvider().readBytes()

                    fileNameToBytes += fileName to fileBytes

                    part.dispose()
                }

                contentRepository.saveFiles(savePath, fileNameToBytes, removeOldContent)
                call.respond(HttpStatusCode.OK)
            } catch (_: FileSaveException) {
                call.respond(HttpStatusCode.InternalServerError)
            }
        }
    }
}