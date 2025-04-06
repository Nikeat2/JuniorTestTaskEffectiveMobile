package com.example.domain

interface FavoriteRepository {

    suspend fun getFavoriteCourses() : List<Course>

    suspend fun deleteACourse(course: Course)

    suspend fun addACourse(course: Course)

}