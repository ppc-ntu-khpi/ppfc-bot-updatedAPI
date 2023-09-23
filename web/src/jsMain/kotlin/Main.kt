/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

import api.apiModule
import app.appModule
import app.main.Main
import com.ppfc_bot.web.web.BuildConfig
import core.coreModule
import core.infrastructure.Logger
import coreui.coreUiModule
import coreui.theme.AppStyleSheet
import infrastructure.infrastructureModule
import onboarding.onboardingModule
import org.jetbrains.compose.web.css.Style
import org.jetbrains.compose.web.renderComposable
import org.koin.core.context.startKoin
import tables.tablesModule

fun main() {
    Logger.setLogLevel(BuildConfig.LOG_LEVEL)

    startKoin {
        modules(
            infrastructureModule,
            apiModule,
            appModule,
            coreModule,
            coreUiModule,
            onboardingModule,
            tablesModule
        )
    }

    renderComposable(rootElementId = "root") {
        Style(AppStyleSheet)

        Main()
    }
}