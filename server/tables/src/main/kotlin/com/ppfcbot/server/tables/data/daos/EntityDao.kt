/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package com.ppfcbot.server.tables.data.daos

import com.ppfcbot.server.tables.data.models.AppEntity

internal typealias TablesEntityDao<T> = EntityDao<Long, T>

internal interface EntityDao<Id, in E : AppEntity<Id>> {
    fun insert(entity: E): Id
    fun update(entity: E)
    fun delete(id: Id)
}