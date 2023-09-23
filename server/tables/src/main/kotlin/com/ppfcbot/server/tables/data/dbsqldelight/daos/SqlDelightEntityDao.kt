/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package com.ppfcbot.server.tables.data.dbsqldelight.daos

import com.ppfcbot.server.tables.data.daos.TablesEntityDao
import com.ppfcbot.server.tables.data.models.AppEntity
import com.ppfcbot.server.tables.database.TablesDatabase

internal interface SqlDelightEntityDao<in E : AppEntity<Long>> : TablesEntityDao<E> {
    val db: TablesDatabase
}