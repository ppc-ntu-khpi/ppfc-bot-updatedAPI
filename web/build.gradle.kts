/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

import org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsRootExtension
import org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsRootPlugin

plugins {
    id(commonLibs.plugins.kotlin.gradle.multiplatform.get().pluginId)
    alias(webLibs.plugins.compose)
    alias(commonLibs.plugins.kotlin.serialization)
    alias(webLibs.plugins.buildconfig)
}

group = "com.ppfc-bot.web"
version = "1.0.0"

kotlin {
    js(IR) {
        nodejs {
            version = "20.7.0"
        }

        browser {}
        binaries.executable()
    }

    sourceSets {
        val jsMain by getting {
            buildConfig {
                buildConfigField(
                    "kotlin.String",
                    "API_BASE_ADDRESS",
                    "\"http://ppfc.us-east-2.elasticbeanstalk.com/api/\""
                )

                buildConfigField(
                    "kotlin.Long",
                    "LOG_LEVEL",
                    "6"
                )
            }

            dependencies {
                implementation(projects.common)
                implementation(compose.web.core)
                implementation(compose.runtime)
                implementation(commonLibs.kotlin.serialization.core)
                implementation(commonLibs.kotlin.serialization.json)
                implementation(webLibs.koin.core)
                implementation(webLibs.koin.core.coroutines)
                implementation(webLibs.koin.compose)
                implementation(webLibs.ktor.client.core)
                implementation(webLibs.ktor.client.js)
                implementation(webLibs.ktor.client.auth)
                implementation(webLibs.ktor.client.content.negotiation)
                implementation(webLibs.ktor.serialization.kotlinx.json)
                implementation(webLibs.paging.common)
            }
        }

        rootProject.plugins.withType(NodeJsRootPlugin::class) {
            rootProject.extensions.getByType(NodeJsRootExtension::class).nodeVersion = "20.7.0"
        }
    }
}