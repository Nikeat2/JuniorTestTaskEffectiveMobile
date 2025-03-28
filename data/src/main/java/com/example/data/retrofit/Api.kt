package com.example.data.retrofit

import com.example.data.models.AllCourses
import retrofit2.http.GET

interface Api {
    @GET("courses")
    suspend fun getAllCourses(): AllCourses
}