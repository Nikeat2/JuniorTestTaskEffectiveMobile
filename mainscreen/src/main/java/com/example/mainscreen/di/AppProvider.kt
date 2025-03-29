package com.example.mainscreen.di

import com.example.data.repository.AppRepository
import com.example.mainscreen.room.CoursesDataBase

interface AppProvider {
    val appRepository: AppRepository
    val room: CoursesDataBase
}