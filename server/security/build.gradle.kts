/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

plugins {
    id(commonLibs.plugins.kotlin.gradle.jvm.get().pluginId)
    alias(commonLibs.plugins.kotlin.serialization)
}

dependencies {
    implementation(projects.common)
    implementation(projects.server.infrastructure)
    implementation(commonLibs.kotlin.serialization.json)
    implementation(serverLibs.aws.cognitoidentityprovider)
    implementation(serverLibs.aws.workspaces)
    implementation(serverLibs.ktor.server.auth)
    implementation(serverLibs.ktor.server.auth.jwt)
    implementation(serverLibs.koin.core)
    implementation(serverLibs.koin.ktor)
}

kotlin {
    jvmToolchain(17)
}