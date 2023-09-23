/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package tables.presentation.screen.tablesbar.bellschedule

import coreui.common.ApiCommonErrorMapper
import coreui.extensions.onSuccess
import coreui.extensions.withErrorMapper
import coreui.extensions.withLoader
import coreui.theme.AppTheme
import coreui.util.ObservableLoadingCounter
import coreui.util.launchWithLoader
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import tables.domain.interactor.GetBellSchedule
import tables.domain.interactor.SaveBellSchedule
import tables.domain.model.BellSchedule

class BellScheduleViewModel(
    private val saveBellSchedule: SaveBellSchedule,
    private val getBellSchedule: GetBellSchedule,
    private val apiCommonErrorMapper: ApiCommonErrorMapper
) {

    private val coroutineScope = CoroutineScope(Dispatchers.Default)

    private val loadingState = ObservableLoadingCounter()
    private val savingState = ObservableLoadingCounter()
    private val _errorLoading = MutableStateFlow(false)
    private val _bellSchedule = MutableStateFlow(BellSchedule.Empty)

    val state: StateFlow<BellScheduleViewState> = combine(
        savingState.observable,
        loadingState.observable,
        _errorLoading,
        _bellSchedule
    ) { isSaving, isLoading, errorLoading, bellSchedule ->
        BellScheduleViewState(
            isSaving = isSaving,
            isLoading = isLoading,
            errorLoading = errorLoading,
            bellSchedule = bellSchedule
        )
    }.stateIn(
        scope = coroutineScope,
        started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000),
        initialValue = BellScheduleViewState.Empty,
    )

    init {
        loadBellSchedule()
    }

    fun loadBellSchedule() {
        getBellSchedule(Unit)
            .withLoader(loadingState)
            .onSuccess { bellSchedule ->
                _bellSchedule.value = bellSchedule
                _errorLoading.value = false
            }.withErrorMapper(
                defaultMessage = AppTheme.stringResources.unexpectedErrorException,
                errorMapper = apiCommonErrorMapper
            ) {
                _errorLoading.value = true
            }.launchIn(coroutineScope)
    }

    fun setBellSchedule(bellSchedule: BellSchedule) {
        _bellSchedule.value = bellSchedule
    }

    fun saveBellSchedule() = launchWithLoader(savingState) {
        saveBellSchedule(
            params = SaveBellSchedule.Params(
                bellSchedule = _bellSchedule.value
            )
        ).collect()
    }
}