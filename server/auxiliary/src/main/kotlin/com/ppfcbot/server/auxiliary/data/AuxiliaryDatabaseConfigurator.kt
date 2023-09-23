/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package com.ppfcbot.server.auxiliary.data

import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import com.ppfcbot.server.auxiliary.database.AuxiliaryDatabase
import com.ppfcbot.server.infrastructure.config.ConfigProvider
import org.sqlite.SQLiteConfig

internal class AuxiliaryDatabaseConfigurator(
    private val configProvider: ConfigProvider
) {

    val instance by lazy {
        configure()
    }

    private fun configure(): AuxiliaryDatabase {
        val config = SQLiteConfig()
        config.enforceForeignKeys(true)
        config.enableCaseSensitiveLike(false)

        val databaseName = configProvider.config.auxiliaryDatabaseName
        val path = "jdbc:sqlite:$databaseName"
        val connectionProperties = config.toProperties()
        val driver = JdbcSqliteDriver(url = path, properties = connectionProperties)

        AuxiliaryDatabase.Schema.create(driver = driver)

        return AuxiliaryDatabase(driver = driver)
    }
}