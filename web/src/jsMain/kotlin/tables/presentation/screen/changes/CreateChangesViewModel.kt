/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package tables.presentation.screen.changes

import app.cash.paging.PagingConfig
import app.cash.paging.PagingData
import app.cash.paging.cachedIn
import core.extensions.combine
import coreui.model.TextFieldState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import tables.domain.interactor.calculateWeekAlternation
import tables.domain.model.*
import tables.domain.observer.ObservePagedClassrooms
import tables.domain.observer.ObservePagedGroups
import tables.domain.observer.ObservePagedSubjects
import tables.domain.observer.ObservePagedTeachers
import tables.presentation.common.mapper.toDayNumber
import tables.presentation.compose.PagingDropDownMenuState
import tables.presentation.screen.changes.mapper.toDomain
import tables.presentation.screen.changes.model.ChangeLessonNumberOption
import tables.presentation.screen.changes.model.ChangeLessonState
import tables.presentation.screen.changes.model.ChangesCommonLessonState
import kotlin.js.Date

class CreateChangesViewModel(
    private val observePagedGroups: ObservePagedGroups,
    private val observePagedClassrooms: ObservePagedClassrooms,
    private val observePagedTeachers: ObservePagedTeachers,
    private val observePagedSubjects: ObservePagedSubjects
) {

    private val coroutineScope = CoroutineScope(Dispatchers.Default)

    private val _changesCommonLesson = MutableStateFlow(ChangesCommonLessonState.Empty)
    private val _changesLessons = MutableStateFlow(
        mapOf(Id.random() to ChangeLessonState.Empty)
    )
    private val isFormBlank = _changesLessons.map { changesLessons ->
        changesLessons.values.any { changeLesson ->
            isChangeLessonNotValid(changeLesson = changeLesson)
        }
    }
    private val canAddLessons = _changesLessons.map { changesLessons ->
        changesLessons.size < 100
    }
    private val canRemoveLessons = _changesLessons.map { changesLessons ->
        changesLessons.size > 1
    }
    private val canAddGroups = _changesLessons.map { changesLessons ->
        changesLessons.map { it.key to (it.value.selectedGroups.size < 20) }.toMap()
    }

    val pagedGroups: Flow<PagingData<Group>> =
        observePagedGroups.flow.cachedIn(coroutineScope)

    val pagedClassrooms: Flow<PagingData<Classroom>> =
        observePagedClassrooms.flow.cachedIn(coroutineScope)

    val pagedTeachers: Flow<PagingData<Teacher>> =
        observePagedTeachers.flow.cachedIn(coroutineScope)

    val pagedSubjects: Flow<PagingData<Subject>> =
        observePagedSubjects.flow.cachedIn(coroutineScope)

    private val _groupsSearchQuery = MutableStateFlow("")
    private val _classroomsSearchQuery = MutableStateFlow("")
    private val _teachersSearchQuery = MutableStateFlow("")
    private val _subjectsSearchQuery = MutableStateFlow("")

    val state: StateFlow<CreateChangesViewState> = combine(
        _changesCommonLesson,
        _changesLessons,
        isFormBlank,
        canAddLessons,
        canRemoveLessons,
        canAddGroups
    ) { changesCommonLesson, changesLessons, isFormBlank, canAddItems, canRemoveItems, canAddGroups ->
        CreateChangesViewState(
            changesCommonLesson = changesCommonLesson,
            changesLessons = changesLessons,
            isFormBlank = isFormBlank,
            canAddLessons = canAddItems,
            canRemoveLessons = canRemoveItems,
            canAddGroups = canAddGroups
        )
    }.stateIn(
        scope = coroutineScope,
        started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000),
        initialValue = CreateChangesViewState.Empty,
    )

    init {
        _groupsSearchQuery.onEach { searchQuery ->
            observePagedGroups(searchQuery = searchQuery)
        }.launchIn(coroutineScope)

        _classroomsSearchQuery.onEach { searchQuery ->
            observePagedClassrooms(searchQuery = searchQuery)
        }.launchIn(coroutineScope)

        _teachersSearchQuery.onEach { searchQuery ->
            observePagedTeachers(searchQuery = searchQuery)
        }.launchIn(coroutineScope)

        _subjectsSearchQuery.onEach { searchQuery ->
            observePagedSubjects(searchQuery = searchQuery)
        }.launchIn(coroutineScope)
    }

    private fun observePagedGroups(
        searchQuery: String? = null
    ) {
        observePagedGroups(
            params = ObservePagedGroups.Params(
                searchQuery = searchQuery,
                pagingConfig = PAGING_CONFIG
            )
        )
    }

    private fun observePagedClassrooms(
        searchQuery: String? = null
    ) {
        observePagedClassrooms(
            params = ObservePagedClassrooms.Params(
                searchQuery = searchQuery,
                pagingConfig = PAGING_CONFIG
            )
        )
    }

    private fun observePagedTeachers(
        searchQuery: String? = null
    ) {
        observePagedTeachers(
            params = ObservePagedTeachers.Params(
                searchQuery = searchQuery,
                pagingConfig = PAGING_CONFIG
            )
        )
    }

    private fun observePagedSubjects(
        searchQuery: String? = null
    ) {
        observePagedSubjects(
            params = ObservePagedSubjects.Params(
                searchQuery = searchQuery,
                pagingConfig = PAGING_CONFIG
            )
        )
    }

    private fun isChangeLessonNotValid(changeLesson: ChangeLessonState): Boolean {
        return changeLesson.selectedGroups.isEmpty()
                || (changeLesson.subjectsMenu.selectedItem == null
                && changeLesson.eventName == TextFieldState.Empty)
    }

    fun getChanges(): List<Change>? {
        val changesCommonLesson = _changesCommonLesson.value
        val changesLessons = _changesLessons.value

        return changesLessons.map { (_, changeLesson) ->
            if (isChangeLessonNotValid(changeLesson = changeLesson)) return null

            changeLesson.toDomain(
                date = changesCommonLesson.date,
                dayNumber = changesCommonLesson.dayNumber,
                weekAlternation = changesCommonLesson.weekAlternation
            )
        }
    }

    fun addChange() {
        _changesLessons.update { items ->
            if (items.size >= 100) return@update items
            items + (Id.random() to ChangeLessonState.Empty)
        }

        _groupsSearchQuery.value = ""
        _classroomsSearchQuery.value = ""
        _teachersSearchQuery.value = ""
        _subjectsSearchQuery.value = ""
    }

    fun removeChange(id: Id.Value) {
        _changesLessons.update { items ->
            if (items.size <= 1) return@update items
            items.filterNot { item ->
                item.key == id
            }
        }
    }

    fun setDate(date: Date) {
        _changesCommonLesson.update { item ->
            item.copy(
                date = date,
                dayNumber = date.toDayNumber(),
                weekAlternation = date.calculateWeekAlternation()
            )
        }
    }

    fun setDayNumber(dayNumber: DayNumber) {
        _changesCommonLesson.update { item ->
            item.copy(dayNumber = dayNumber)
        }
    }

    fun setWeekAlternation(weekAlternation: WeekAlternation) {
        _changesCommonLesson.update { item ->
            item.copy(weekAlternation = weekAlternation)
        }
    }

    fun addGroup(id: Id.Value, group: Group) {
        _changesLessons.update { items ->
            val item = items[id] ?: return@update items
            if (item.selectedGroups.size >= 20) return@update items
            items + (id to item.copy(selectedGroups = item.selectedGroups + group))
        }
    }

    fun removeGroup(id: Id.Value, group: Group) {
        _changesLessons.update { items ->
            val item = items[id] ?: return@update items
            items + (id to item.copy(selectedGroups = item.selectedGroups - group))
        }
    }

    fun setGroup(id: Id.Value, groupsMenu: PagingDropDownMenuState<Group>) {
        _changesLessons.update { items ->
            val item = items[id] ?: return@update items
            items + (id to item.copy(groupsMenu = groupsMenu.copy(selectedItem = null)))
        }
        _groupsSearchQuery.value = groupsMenu.searchQuery
    }

    fun setClassroom(id: Id.Value, classroomsMenu: PagingDropDownMenuState<Classroom>) {
        _changesLessons.update { items ->
            val item = items[id] ?: return@update items
            items + (id to item.copy(classroomsMenu = classroomsMenu))
        }
        _classroomsSearchQuery.value = classroomsMenu.searchQuery
    }

    fun setTeacher(id: Id.Value, teachersMenu: PagingDropDownMenuState<Teacher>) {
        _changesLessons.update { items ->
            val item = items[id] ?: return@update items
            items + (id to item.copy(teachersMenu = teachersMenu))
        }
        _teachersSearchQuery.value = teachersMenu.searchQuery
    }

    fun setEventName(id: Id.Value, eventName: String) {
        _changesLessons.update { items ->
            val item = items[id] ?: return@update items
            items + (id to item.copy(
                eventName = item.eventName.copy(text = eventName),
                subjectsMenu = PagingDropDownMenuState.Empty()
            ))
        }
    }

    fun setSubject(id: Id.Value, subjectsMenu: PagingDropDownMenuState<Subject>) {
        _changesLessons.update { items ->
            val item = items[id] ?: return@update items
            items + (id to item.copy(
                subjectsMenu = subjectsMenu,
                eventName = TextFieldState.Empty
            ))
        }
        _subjectsSearchQuery.value = subjectsMenu.searchQuery
    }

    fun setLessonNumber(id: Id.Value, lessonNumber: ChangeLessonNumberOption) {
        _changesLessons.update { items ->
            val item = items[id] ?: return@update items
            items + (id to item.copy(lessonNumber = lessonNumber))
        }
    }

    private companion object {
        val PAGING_CONFIG = PagingConfig(
            pageSize = 10,
            prefetchDistance = 20
        )
    }
}