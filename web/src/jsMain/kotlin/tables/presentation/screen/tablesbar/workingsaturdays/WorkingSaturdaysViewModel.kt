/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package tables.presentation.screen.tablesbar.workingsaturdays

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
import tables.domain.interactor.GetWorkingSaturdays
import tables.domain.interactor.SaveWorkingSaturdays
import tables.domain.model.WorkingSaturdays

class WorkingSaturdaysViewModel(
    private val saveWorkingSaturdays: SaveWorkingSaturdays,
    private val getWorkingSaturdays: GetWorkingSaturdays,
    private val apiCommonErrorMapper: ApiCommonErrorMapper
) {

    private val coroutineScope = CoroutineScope(Dispatchers.Default)

    private val loadingState = ObservableLoadingCounter()
    private val savingState = ObservableLoadingCounter()
    private val _errorLoading = MutableStateFlow(false)
    private val _workingSaturdays = MutableStateFlow(WorkingSaturdays.Empty)

    val state: StateFlow<WorkingSaturdaysViewState> = combine(
        savingState.observable,
        loadingState.observable,
        _errorLoading,
        _workingSaturdays
    ) { isSaving, isLoading, errorLoading, bellSchedule ->
        WorkingSaturdaysViewState(
            isSaving = isSaving,
            isLoading = isLoading,
            errorLoading = errorLoading,
            workingSaturdays = bellSchedule
        )
    }.stateIn(
        scope = coroutineScope,
        started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000),
        initialValue = WorkingSaturdaysViewState.Empty,
    )

    init {
        loadWorkingSaturdays()
    }

    fun loadWorkingSaturdays() {
        getWorkingSaturdays(Unit)
            .withLoader(loadingState)
            .onSuccess { bellSchedule ->
                _workingSaturdays.value = bellSchedule
                _errorLoading.value = false
            }.withErrorMapper(
                defaultMessage = AppTheme.stringResources.unexpectedErrorException,
                errorMapper = apiCommonErrorMapper
            ) {
                _errorLoading.value = true
            }.launchIn(coroutineScope)
    }

    fun setWorkingSaturdays(workingSaturdays: WorkingSaturdays) {
        _workingSaturdays.value = workingSaturdays
    }

    fun saveWorkingSaturdays() = launchWithLoader(savingState) {
        saveWorkingSaturdays(
            params = SaveWorkingSaturdays.Params(
                workingSaturdays = _workingSaturdays.value
            )
        ).collect()
    }
}