package com.example.domain

interface AppRepository {

    suspend fun getAllCourses() : List<Course>

}