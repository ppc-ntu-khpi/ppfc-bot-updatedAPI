/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package com.ppfcbot.server.auxiliary.data.bellschedule

import com.ppfcbot.server.auxiliary.database.AuxiliaryDatabase

internal class SqlDelightBellScheduleDao(
    private val db: AuxiliaryDatabase
) : BellScheduleDao {

    override fun insert(bellSchedule: BellSchedule) {
        db.transaction {
            delete()
            db.bellScheduleQueries.insert(bellSchedule.text)
        }
    }

    override fun delete() {
        db.bellScheduleQueries.delete()
    }

    override fun get(): BellSchedule? {
        return db.bellScheduleQueries.select().executeAsOneOrNull()?.let {
            BellSchedule(it)
        }
    }
}