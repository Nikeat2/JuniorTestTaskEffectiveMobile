package com.example.mainscreen.di

import com.example.mainscreen.homepage.view.MainScreenFragment
import com.example.mainscreen.homepage.viewmodel.HomePageViewModel
import dagger.Subcomponent

@Subcomponent(modules = [MainScreenModule::class])
interface MainScreenComponent {
    fun inject(mainScreenFragment: MainScreenFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(): MainScreenComponent
    }
}