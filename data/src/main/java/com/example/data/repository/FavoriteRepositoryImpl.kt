package com.example.data.repository

import com.example.data.mappers.fromCourseEntityToCourse.MapperFromCourseEntityToCourse
import com.example.data.mappers.fromCourseToCourseEntity.MapperFromCourseToCourseEntity
import com.example.data.room.CoursesDataBase
import com.example.domain.Course
import com.example.domain.FavoriteRepository
import javax.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(
    private val room: CoursesDataBase,
    private val mapperToCourse: MapperFromCourseEntityToCourse,
    private val mapperToCourseEntity: MapperFromCourseToCourseEntity


) :
    FavoriteRepository {

    override suspend fun getFavoriteCourses(): List<Course> =
        mapperToCourse.mapToCourse(room.getCourseDao().getFavoriteCourses())

    override suspend fun deleteACourse(course: Course) {
        room.getCourseDao().deleteACourse(mapperToCourseEntity.mapToCourseEntity(course))
    }

    override suspend fun addACourse(course: Course) {
        room.getCourseDao().insertACourse(mapperToCourseEntity.mapToCourseEntity(course))
    }


}