/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package tables

import org.koin.dsl.module
import tables.data.dao.*
import tables.data.repository.*
import tables.domain.interactor.*
import tables.domain.observer.*
import tables.domain.repository.*
import tables.presentation.common.mapper.TablesCommonErrorMapper
import tables.presentation.screen.changes.ChangesViewModel
import tables.presentation.screen.changes.CreateChangesViewModel
import tables.presentation.screen.changes.EditChangeViewModel
import tables.presentation.screen.classrooms.ClassroomsViewModel
import tables.presentation.screen.classrooms.ManageClassroomViewModel
import tables.presentation.screen.courses.CoursesViewModel
import tables.presentation.screen.courses.ManageCourseViewModel
import tables.presentation.screen.disciplines.DisciplinesViewModel
import tables.presentation.screen.disciplines.ManageDisciplineViewModel
import tables.presentation.screen.groups.GroupsViewModel
import tables.presentation.screen.groups.ManageGroupViewModel
import tables.presentation.screen.schedule.CreateScheduleItemsViewModel
import tables.presentation.screen.schedule.EditScheduleItemViewModel
import tables.presentation.screen.schedule.ScheduleViewModel
import tables.presentation.screen.subjects.ManageSubjectViewModel
import tables.presentation.screen.subjects.SubjectsViewModel
import tables.presentation.screen.tablesbar.TablesViewModel
import tables.presentation.screen.tablesbar.accesskey.AccessKeyViewModel
import tables.presentation.screen.tablesbar.bellschedule.BellScheduleViewModel
import tables.presentation.screen.tablesbar.workingsaturdays.WorkingSaturdaysViewModel
import tables.presentation.screen.teachers.ManageTeacherViewModel
import tables.presentation.screen.teachers.TeachersViewModel
import tables.presentation.screen.users.UsersViewModel

val tablesModule = module {
    single {
        TablesCommonErrorMapper()
    }

    factory {
        TablesViewModel(get(), get(), get())
    }

    factory {
        AccessKeyViewModel(get(), get())
    }

    /* Classrooms */
    single<ClassroomsDao> {
        ClassroomsDaoImpl(get())
    }

    single<ClassroomsRepository> {
        ClassroomsRepositoryImpl(get())
    }

    single {
        SaveClassroom(get())
    }

    single {
        DeleteClassrooms(get())
    }

    single {
        ObservePagedClassrooms(get())
    }

    factory {
        ClassroomsViewModel(get(), get(), get(), get())
    }

    factory {
        ManageClassroomViewModel()
    }

    /* Courses */
    single<CoursesDao> {
        CoursesDaoImpl(get())
    }

    single<CoursesRepository> {
        CoursesRepositoryImpl(get())
    }

    single {
        SaveCourse(get())
    }

    single {
        DeleteCourses(get())
    }

    single {
        ObservePagedCourses(get())
    }

    factory {
        CoursesViewModel(get(), get(), get(), get())
    }

    factory {
        ManageCourseViewModel()
    }

    /* Disciplines */
    single<DisciplinesDao> {
        DisciplinesDaoImpl(get())
    }

    single<DisciplinesRepository> {
        DisciplinesRepositoryImpl(get())
    }

    single {
        SaveDiscipline(get())
    }

    single {
        DeleteDisciplines(get())
    }

    single {
        ObservePagedDisciplines(get())
    }

    factory {
        DisciplinesViewModel(get(), get(), get(), get())
    }

    factory {
        ManageDisciplineViewModel()
    }

    /* Subjects */
    single<SubjectsDao> {
        SubjectsDaoImpl(get())
    }

    single<SubjectsRepository> {
        SubjectsRepositoryImpl(get())
    }

    single {
        SaveSubject(get())
    }

    single {
        DeleteSubjects(get())
    }

    single {
        ObservePagedSubjects(get())
    }

    factory {
        SubjectsViewModel(get(), get(), get(), get())
    }

    factory {
        ManageSubjectViewModel()
    }

    /* Teachers */
    single<TeachersDao> {
        TeachersDaoImpl(get())
    }

    single<TeachersRepository> {
        TeachersRepositoryImpl(get())
    }

    single {
        SaveTeacher(get())
    }

    single {
        DeleteTeachers(get())
    }

    single {
        ObservePagedTeachers(get())
    }

    factory {
        TeachersViewModel(get(), get(), get(), get(), get())
    }

    factory {
        ManageTeacherViewModel(get())
    }

    /* Groups */
    single<GroupsDao> {
        GroupsDaoImpl(get())
    }

    single<GroupsRepository> {
        GroupsRepositoryImpl(get())
    }

    single {
        SaveGroup(get())
    }

    single {
        DeleteGroups(get())
    }

    single {
        ObservePagedGroups(get())
    }

    factory {
        GroupsViewModel(get(), get(), get(), get(), get())
    }

    factory {
        ManageGroupViewModel(get())
    }

    /* Users */
    single<UsersDao> {
        UsersDaoImpl(get())
    }

    single<UsersRepository> {
        UsersRepositoryImpl(get())
    }

    single {
        DeleteUsers(get())
    }

    single {
        ObservePagedUsers(get())
    }

    factory {
        UsersViewModel(get(), get(), get())
    }

    /* Schedule */
    single<ScheduleDao> {
        ScheduleDaoImpl(get())
    }

    single<ScheduleRepository> {
        ScheduleRepositoryImpl(get())
    }

    single {
        ProcessAndValidateScheduleItem()
    }

    single {
        SaveScheduleItem(get(), get())
    }

    single {
        SaveScheduleItems(get(), get())
    }

    single {
        DeleteScheduleItems(get())
    }

    single {
        DeleteAllScheduleItems(get())
    }

    single {
        ObservePagedSchedule(get())
    }

    factory {
        ScheduleViewModel(get(), get(), get(), get(), get(), get(), get(), get(), get())
    }

    factory {
        CreateScheduleItemsViewModel(get(), get(), get(), get())
    }

    factory {
        EditScheduleItemViewModel(get(), get(), get(), get())
    }

    /* Changes */
    single<ChangesDao> {
        ChangesDaoImpl(get())
    }

    single<ChangesRepository> {
        ChangesRepositoryImpl(get())
    }

    single {
        ProcessAndValidateChange()
    }

    single {
        SaveChange(get(), get())
    }

    single {
        SaveChanges(get(), get())
    }

    single {
        DeleteChanges(get())
    }

    single {
        DeleteAllChanges(get())
    }

    single {
        ExportChangesToDocument(get())
    }

    single {
        ObservePagedChanges(get())
    }

    factory {
        ChangesViewModel(get(), get(), get(), get(), get(), get(), get(), get(), get())
    }

    factory {
        CreateChangesViewModel(get(), get(), get(), get())
    }

    factory {
        EditChangeViewModel(get(), get(), get(), get())
    }

    /* Access key */
    single<AccessKeyDao> {
        AccessKeyDaoImpl(get())
    }

    single<AccessKeyRepository> {
        AccessKeyRepositoryImpl(get())
    }

    single {
        GenerateAccessKey(get())
    }

    /* Bell schedule */
    single<BellScheduleDao> {
        BellScheduleDaoImpl(get())
    }

    single<BellScheduleRepository> {
        BellScheduleRepositoryImpl(get())
    }

    single {
        SaveBellSchedule(get())
    }

    single {
        GetBellSchedule(get())
    }

    factory {
        BellScheduleViewModel(get(), get(), get())
    }

    /* Working saturdays */
    single<WorkingSaturdaysDao> {
        WorkingSaturdaysDaoImpl(get())
    }

    single<WorkingSaturdaysRepository> {
        WorkingSaturdaysRepositoryImpl(get())
    }

    single {
        SaveWorkingSaturdays(get())
    }

    single {
        GetWorkingSaturdays(get())
    }

    factory {
        WorkingSaturdaysViewModel(get(), get(), get())
    }
}