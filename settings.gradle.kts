/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
        google()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

dependencyResolutionManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }

    versionCatalogs {
        create("commonLibs") {
            from(files("/gradle/common-libs.versions.toml"))
        }

        create("serverLibs") {
            from(files("/gradle/server-libs.versions.toml"))
        }

        create("webLibs") {
            from(files("/gradle/web-libs.versions.toml"))
        }
    }
}

rootProject.name = "ppfc-bot"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

include(
    "common",
    "server",
    "server:infrastructure",
    "server:security",
    "server:tables",
    "server:content",
    "server:auxiliary",
    "web",
)