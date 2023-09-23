/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package com.ppfcbot.server.tables.data.dbsqldelight

import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import com.ppfcbot.server.infrastructure.config.ConfigProvider
import com.ppfcbot.server.tables.database.TablesDatabase
import org.sqlite.SQLiteConfig

internal class TablesDatabaseConfigurator(
    private val configProvider: ConfigProvider
) {

    val instance by lazy {
        configure()
    }

    private fun configure(): TablesDatabase {
        val config = SQLiteConfig()
        config.enforceForeignKeys(true)
        config.enableCaseSensitiveLike(false)

        val databaseName = configProvider.config.tablesDatabaseName
        val path = "jdbc:sqlite:$databaseName"
        val connectionProperties = config.toProperties()
        val driver = JdbcSqliteDriver(url = path, properties = connectionProperties)

        TablesDatabase.Schema.create(driver = driver)

        return TablesDatabase(driver = driver)
    }
}