/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package tables.presentation.screen.tablesbar

import core.domain.interactor.SaveColorScheme
import core.domain.model.ColorSchemeMode
import core.domain.observer.ObservePreferences
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import onboarding.domain.interactor.LogOut

class TablesViewModel(
    observePreferences: ObservePreferences,
    private val saveColorScheme: SaveColorScheme,
    private val logOut: LogOut
) {

    private val coroutineScope = CoroutineScope(Dispatchers.Default)

    private val _dialog = MutableStateFlow<TablesDialog?>(null)

    val state: StateFlow<TablesViewState> = combine(
        observePreferences.flow,
        _dialog,
    ) { preferences, dialog ->
        TablesViewState(
            preferences = preferences,
            dialog = dialog,
        )
    }.stateIn(
        scope = coroutineScope,
        started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000),
        initialValue = TablesViewState.Empty,
    )

    init {
        observePreferences(Unit)
    }

    fun setColorSchemeMode(colorSchemeMode: ColorSchemeMode) {
        saveColorScheme(
            params = SaveColorScheme.Params(
                colorSchemeMode = colorSchemeMode
            )
        ).launchIn(coroutineScope)
    }

    fun logOut() {
        logOut(Unit).launchIn(coroutineScope)
    }

    fun dialog(dialog: TablesDialog?) {
        _dialog.value = dialog
    }
}