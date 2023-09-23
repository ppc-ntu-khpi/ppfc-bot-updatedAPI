/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package com.ppfcbot.server.tables.data.dbsqldelight.utils

fun Boolean.toSqlBoolean() = if (this) 1L else 0L

fun Long.toBoolean() = this == 1L