/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package com.ppfcbot.server.auxiliary.data.workingsaturdays

internal interface WorkingSaturdaysDao {
    fun insert(workingSaturdays: WorkingSaturdays)
    fun delete()
    fun get(): WorkingSaturdays?
}