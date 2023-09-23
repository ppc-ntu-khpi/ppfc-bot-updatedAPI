/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

plugins {
    id(commonLibs.plugins.kotlin.gradle.jvm.get().pluginId)
}

dependencies {
    implementation(serverLibs.koin.core)
}

kotlin {
    jvmToolchain(17)
}