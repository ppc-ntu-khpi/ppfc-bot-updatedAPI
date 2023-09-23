/*
 * Copyright (c) 2023. Vitalii Kozyr
 */

package com.ppfcbot.server.tables.data

import com.ppfcbot.server.tables.data.daos.*
import com.ppfcbot.server.tables.data.dbsqldelight.TablesDatabaseConfigurator
import com.ppfcbot.server.tables.data.dbsqldelight.daos.*
import com.ppfcbot.server.tables.data.repositories.*
import org.koin.dsl.module

internal val persistenceModule = module {
    single {
        TablesDatabaseConfigurator(get()).instance
    }

    single<ChangeDao> {
        SqlDelightChangeDao(get())
    }

    single<ClassroomDao> {
        SqlDelightClassroomDao(get())
    }

    single<CourseDao> {
        SqlDelightCourseDao(get())
    }

    single<DisciplineDao> {
        SqlDelightDisciplineDao(get())
    }

    single<GroupDao> {
        SqlDelightGroupDao(get())
    }

    single<ScheduleDao> {
        SqlDelightScheduleDao(get())
    }

    single<SubjectDao> {
        SqlDelightSubjectDao(get())
    }

    single<TeacherDao> {
        SqlDelightTeacherDao(get())
    }

    single<UserDao> {
        SqlDelightUserDao(get())
    }

    single<ChangeRepository> {
        ChangeRepositoryImpl(get())
    }

    single<ClassroomRepository> {
        ClassroomRepositoryImpl(get())
    }

    single<CourseRepository> {
        CourseRepositoryImpl(get())
    }

    single<DisciplineRepository> {
        DisciplineRepositoryImpl(get())
    }

    single<GroupRepository> {
        GroupRepositoryImpl(get())
    }

    single<ScheduleRepository> {
        ScheduleRepositoryImpl(get())
    }

    single<SubjectRepository> {
        SubjectRepositoryImpl(get())
    }

    single<TeacherRepository> {
        TeacherRepositoryImpl(get())
    }

    single<UserRepository> {
        UserRepositoryImpl(get())
    }
}