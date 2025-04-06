package com.example.data.repository

import com.example.data.mappers.fromCourseDtoToCourse.MapperFromCourseDtoToCourse
import com.example.data.retrofit.Api
import com.example.domain.AppRepository
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(
    private val allPersonApi: Api,
    private val mapper: MapperFromCourseDtoToCourse
) : AppRepository {
    override suspend fun getAllCourses() =
        mapper.mapFromCourseDtoToCourse(allPersonApi.getAllCourses().courses)
}