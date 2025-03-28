package com.example.domain

import com.example.data.repository.AppRepository
import com.example.data.retrofit.Api
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(private val allPersonApi: Api) : AppRepository {
    override suspend fun getAllCourses() = allPersonApi.getAllCourses()
}