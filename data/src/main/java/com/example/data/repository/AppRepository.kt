package com.example.data.repository

import com.example.data.models.AllCourses

interface AppRepository {

    suspend fun getAllCourses() : AllCourses
}