/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

plugins {
    id(commonLibs.plugins.kotlin.gradle.multiplatform.get().pluginId)
    alias(commonLibs.plugins.kotlin.serialization)
}

group = "com.ppfc-bot.common"
version = "1.0.0"

kotlin {
    sourceSets {
        jvm {
            jvmToolchain(17)
            withJava()
        }
        js {
            browser {
                commonWebpackConfig {
                    cssSupport {
                        enabled.set(true)
                    }
                }
            }
        }

        val commonMain by getting {
            dependencies {
                implementation(commonLibs.kotlin.serialization.json)
            }
        }

        val jvmMain by getting
        val jsMain by getting
    }
}