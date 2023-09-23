/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package com.ppfcbot.server.auxiliary.data.bellschedule

internal interface BellScheduleDao {
    fun insert(bellSchedule: BellSchedule)
    fun delete()
    fun get(): BellSchedule?
}