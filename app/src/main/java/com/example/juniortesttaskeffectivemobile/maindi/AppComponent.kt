package com.example.juniortesttaskeffectivemobile.maindi

import android.app.Application
import android.content.Context
import com.example.data.repository.AppRepository
import com.example.data.retrofit.Api
import com.example.mainscreen.di.MainScreenComponent
import com.example.mainscreen.homepage.view.MainScreenFragment
import com.example.mainscreen.room.CoursesDataBase
import dagger.BindsInstance
import dagger.Component

interface AppDependencies {
    fun context(): Context
    fun api(): Api
    fun room(): CoursesDataBase
    fun repository(): AppRepository
}

@Component(modules = [AppModule::class])
interface AppComponent : AppDependencies {

    fun inject(mainScreenFragment: MainScreenFragment)
    fun mainScreenComponent(): MainScreenComponent.Factory

    @Component.Builder
    interface Builder {
        fun appModule(appModule: AppModule): Builder

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}

