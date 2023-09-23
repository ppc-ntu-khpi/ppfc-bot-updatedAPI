/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package com.ppfcbot.server.auxiliary.data.workingsaturdays

import com.ppfcbot.server.auxiliary.database.AuxiliaryDatabase

internal class SqlDelightWorkingSaturdaysDao(
    private val db: AuxiliaryDatabase
) : WorkingSaturdaysDao {

    override fun insert(workingSaturdays: WorkingSaturdays) {
        db.transaction {
            delete()
            db.workingSaturdaysQueries.insert(workingSaturdays.text)
        }
    }

    override fun delete() {
        db.workingSaturdaysQueries.delete()
    }

    override fun get(): WorkingSaturdays? {
        return db.workingSaturdaysQueries.select().executeAsOneOrNull()?.let {
            WorkingSaturdays(it)
        }
    }
}