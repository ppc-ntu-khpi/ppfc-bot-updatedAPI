/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

plugins {
    id(commonLibs.plugins.kotlin.gradle.jvm.get().pluginId)
    alias(serverLibs.plugins.sqldelight)
}

dependencies {
    implementation(projects.common)
    implementation(projects.server.infrastructure)
    implementation(serverLibs.ktor.server.core.jvm)
    implementation(serverLibs.ktor.server.auth)
    implementation(serverLibs.koin.core)
    implementation(serverLibs.koin.ktor)
    implementation(serverLibs.ktor.server.core.jvm)
    implementation(serverLibs.sqldelight.sqlite.driver)
    implementation(serverLibs.sqldelight.coroutines.extensions)
    implementation(serverLibs.sqlite.jdbc)
}

kotlin {
    jvmToolchain(17)
}

sqldelight {
    databases {
        create("AuxiliaryDatabase") {
            packageName.set("com.ppfcbot.server.auxiliary.database")
        }
    }
}