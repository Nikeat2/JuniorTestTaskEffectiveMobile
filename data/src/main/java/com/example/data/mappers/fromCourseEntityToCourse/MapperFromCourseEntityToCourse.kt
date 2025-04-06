package com.example.data.mappers.fromCourseEntityToCourse

import com.example.data.models.CourseEntity
import com.example.domain.Course

interface MapperFromCourseEntityToCourse {

    fun mapToCourse(courseEntity: List<CourseEntity>): List<Course>

}