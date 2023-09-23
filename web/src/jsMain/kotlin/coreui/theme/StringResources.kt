/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package coreui.theme

import core.domain.model.Locale

fun getStringResources(locale: Locale): StringResources {
    return when(locale) {
        Locale.UA -> uaStringResources
    }
}

data class StringResources(
    val yes: String,
    val no: String,
    val dropDownMenuNotSelected: String,

    val unexpectedErrorException: String,
    val authenticationException: String,
    val networkException: String,
    val timeoutException: String,
    val newPasswordRequiredChallengeFailed: String,
    val formIsNotValidException: String,
    val noChangesException: String,

    val passwordIsNotInvalid: String,

    val loginTitle: String,
    val loginUsernameFieldLabel: String,
    val loginPasswordFieldLabel: String,
    val loginLogInButton: String,

    val changePasswordTitle: String,
    val changePasswordPasswordFieldLabel: String,
    val changePasswordChangePasswordButton: String,
    val changePasswordLogIn: String,
    val changePasswordInAccount: String,
    val changePasswordShowPassword: String,

    val navigationChanges: String,
    val navigationSchedule: String,
    val navigationDisciplines: String,
    val navigationClassrooms: String,
    val navigationCourses: String,
    val navigationSubjects: String,
    val navigationGroups: String,
    val navigationTeachers: String,
    val navigationUsers: String,
    val navigationOther: String,

    val tablesShowAccessKeyTitle: String,
    val tablesShowAccessKeyExpiresAt: String,
    val tablesShowAccessKeyClose: String,
    val tablesShowAccessKeyErrorLoading: String,
    val tablesShowAccessKeyRetry: String,

    val tablesShowBellScheduleTitle: String,
    val tablesShowBellScheduleTextFieldTitle: String,
    val tablesShowBellScheduleSave: String,
    val tablesShowBellScheduleCancel: String,
    val tablesShowBellScheduleErrorLoading: String,
    val tablesShowBellScheduleRetry: String,

    val tablesShowWorkingSaturdaysTitle: String,
    val tablesShowWorkingSaturdaysTextFieldTitle: String,
    val tablesShowWorkingSaturdaysSave: String,
    val tablesShowWorkingSaturdaysCancel: String,
    val tablesShowWorkingSaturdaysErrorLoading: String,
    val tablesShowWorkingSaturdaysRetry: String,

    val tableDialogDeleteTitle: String,
    val tableDialogEditTitle: String,
    val tableRecordsNotFound: String,
    val tableAdd: String,
    val tableDelete: String,
    val tableDeleteRowsWarning: String,
    val tableDeleteAllRowsWarning: String,
    val tableDeleteRowsConfirm: String,
    val tableDeleteRowsCancel: String,
    val tableManageItemDialogSave: String,
    val tableManageItemDialogCancel: String,

    val classroomsName: String,
    val classroomsSearchLabel: String,

    val coursesNumber: String,
    val coursesSearchLabel: String,

    val disciplinesName: String,
    val disciplinesSearchLabel: String,

    val subjectsName: String,
    val subjectsSearchLabel: String,

    val teachersFirstName: String,
    val teachersLastName: String,
    val teacherMiddleName: String,
    val teachersDiscipline: String,
    val teachersIsHeadTeacher: String,
    val teachersSearchLabel: String,
    val teachersFilterByDisciplineLabel: String,

    val groupsNumber: String,
    val groupsCourseNumber: String,
    val groupsSearchLabel: String,
    val groupsFilterByCourseLabel: String,

    val usersId: String,
    val usersUser: String,
    val usersGroup: String,
    val usersSearchLabel: String,

    val scheduleGroupNumber: String,
    val scheduleClassroomName: String,
    val scheduleTeacher: String,
    val scheduleSubject: String,
    val scheduleEventName: String,
    val scheduleSubjectOrEventName: String,
    val scheduleLessonNumber: String,
    val scheduleDayNumber: String,
    val scheduleWeekAlternation: String,
    val scheduleFilterByGroupLabel: String,
    val scheduleFilterByTeacherLabel: String,
    val scheduleFilterByDayNumber: String,
    val scheduleFilterByWeekAlternation: String,
    val scheduleAddSubject: String,
    val scheduleLessons: String,

    val changesGroupNumber: String,
    val changesClassroomName: String,
    val changesTeacher: String,
    val changesSubject: String,
    val changesEventName: String,
    val changesSubjectOrEventName: String,
    val changesLessonNumber: String,
    val changesDayNumber: String,
    val changesWeekAlternation: String,
    val changesFilterByGroupLabel: String,
    val changesFilterByTeacherLabel: String,
    val changesDate: String,
    val changesFilterByWeekAlternation: String,
    val changesAddSubject: String,
    val changesLessons: String,
    val createChangesGroupIsNotSelected: String,

    val dayNumberAll: String,
    val monday: String,
    val tuesday: String,
    val wednesday: String,
    val thursday: String,
    val friday: String,
    val saturday: String,
    val sunday: String,

    val weekAlternationAll: String,
    val weekAlternationNumerator: String,
    val weekAlternationDenominator: String,
)