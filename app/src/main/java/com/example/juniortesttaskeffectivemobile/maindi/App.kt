package com.example.juniortesttaskeffectivemobile.maindi

import android.app.Application
import androidx.room.Room
import com.example.data.repository.AppRepository
import com.example.domain.AppRepositoryImpl
import com.example.mainscreen.di.AppProvider
import com.example.mainscreen.room.CoursesDataBase

class App : Application(), AppProvider {
    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .application(this)
            .appModule(AppModule(this))
            .build()
    }

    override val appRepository: AppRepository by lazy {
        AppRepositoryImpl(appComponent.api())
    }
    override val room: CoursesDataBase by lazy {
        Room.databaseBuilder(applicationContext, CoursesDataBase::class.java, "file").build()
    }

}
