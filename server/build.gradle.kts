/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

plugins {
    id(commonLibs.plugins.kotlin.gradle.jvm.get().pluginId)
    alias(serverLibs.plugins.ktor)
}

group = "com.ppfc-bot.server"
version = "1.0.0"

application {
    mainClass.set("io.ktor.server.netty.EngineMain")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

dependencies {
    implementation(projects.server.infrastructure)
    implementation(projects.server.security)
    implementation(projects.server.tables)
    implementation(projects.server.content)
    implementation(projects.server.auxiliary)
    implementation(serverLibs.ktor.server.core.jvm)
    implementation(serverLibs.ktor.server.cors)
    implementation(serverLibs.ktor.server.contentNegotiation)
    implementation(serverLibs.ktor.network.tls.certificates)
    implementation(serverLibs.ktor.serialization.kotlinx.json)
    implementation(serverLibs.ktor.server.netty.jvm)
    implementation(serverLibs.koin.ktor)
    implementation(serverLibs.koin.core)
}

kotlin {
    jvmToolchain(17)
}

ktor {
    fatJar {
        archiveFileName.set("api.jar")
    }
}

val clearDist = tasks.register("clearDist") {
    doLast {
        delete("build/dist")
    }
}

val copyApiJarToDist = tasks.register("copyApiJarToDist", Copy::class) {
    dependsOn("buildFatJar")
    dependsOn(clearDist)

    from("build/libs")
    include("api.jar")
    into("build/dist")
}

val createProcfile = tasks.register("createProcfile") {
    doLast {
        file("build/dist").mkdirs()
        file("build/dist/Procfile").also {
            it.createNewFile()
            it.appendText("web: java -Xms256m -jar api.jar")
        }
    }
}

val createRunBat = tasks.register("createRunBat") {
    doLast {
        file("build/dist").mkdirs()
        file("build/dist/run.bat").also {
            it.createNewFile()
            it.appendText("java -jar api.jar\npause")
        }
    }
}

tasks.register("distribute", Zip::class) {
    dependsOn(copyApiJarToDist)
    dependsOn(createProcfile)
    dependsOn(createRunBat)

    from("build/dist")
    include("api.jar")
    include("Procfile")
    include("run.bat")
    archiveFileName.set("api-$version.zip")
    destinationDirectory = file("build/dist")
}

tasks.named("distribute") {
    group = "server"
    description = "Creates server distribution under build/dist folder."
}