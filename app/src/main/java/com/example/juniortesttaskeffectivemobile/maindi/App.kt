package com.example.juniortesttaskeffectivemobile.maindi

import android.app.Application
import androidx.room.Room
import com.example.data.mappers.fromCourseDtoToCourse.MapperFromCourseDtoToCourseImpl
import com.example.data.mappers.fromCourseEntityToCourse.MapperFromCourseEntityToCourseImpl
import com.example.data.mappers.fromCourseToCourseEntity.MapperFromCourseToCourseEntityImpl
import com.example.data.repository.AppRepositoryImpl
import com.example.data.repository.FavoriteRepositoryImpl
import com.example.data.room.CoursesDataBase
import com.example.domain.AppRepository
import com.example.domain.FavoriteRepository
import com.example.mainscreen.di.AppProvider

class App : Application(), AppProvider {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .application(this)
            .appModule(AppModule(this))
            .build()
    }

    override val appRepository: AppRepository by lazy {
        AppRepositoryImpl(appComponent.api(), MapperFromCourseDtoToCourseImpl())
    }
    override val room: CoursesDataBase by lazy {
        Room.databaseBuilder(applicationContext, CoursesDataBase::class.java, "file").build()
    }


    override val favoriteRepository: FavoriteRepository by lazy {
        FavoriteRepositoryImpl(
            room,
            MapperFromCourseEntityToCourseImpl(),
            MapperFromCourseToCourseEntityImpl()
        )
    }

}
