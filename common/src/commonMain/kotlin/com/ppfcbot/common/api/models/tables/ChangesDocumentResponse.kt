/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package com.ppfcbot.common.api.models.tables

import kotlinx.serialization.Serializable

@Serializable
data class ChangesDocumentResponse(
    val fileName: String? = null,
    val fileBytes: ByteArray? = null,
    val error: String? = null
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as ChangesDocumentResponse

        if (fileName != other.fileName) return false
        if (fileBytes != null) {
            if (other.fileBytes == null) return false
            if (!fileBytes.contentEquals(other.fileBytes)) return false
        } else if (other.fileBytes != null) return false
        if (error != other.error) return false

        return true
    }

    override fun hashCode(): Int {
        var result = fileName?.hashCode() ?: 0
        result = 31 * result + (fileBytes?.contentHashCode() ?: 0)
        result = 31 * result + (error?.hashCode() ?: 0)
        return result
    }
}