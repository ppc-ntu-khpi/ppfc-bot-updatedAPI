/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

plugins {
    id(commonLibs.plugins.kotlin.gradle.jvm.get().pluginId)
}

dependencies {
    implementation(projects.server.infrastructure)
    implementation(serverLibs.koin.core)
    implementation(serverLibs.ktor.server.core.jvm)
    implementation(serverLibs.ktor.server.auth)
    implementation(serverLibs.koin.ktor)
}

kotlin {
    jvmToolchain(17)
}