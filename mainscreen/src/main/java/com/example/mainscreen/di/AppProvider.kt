package com.example.mainscreen.di

import com.example.domain.AppRepository
import com.example.domain.FavoriteRepository
import com.example.data.room.CoursesDataBase

interface AppProvider {
    val appRepository: AppRepository
    val room: CoursesDataBase
    val favoriteRepository: FavoriteRepository
}