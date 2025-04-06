package com.example.data.mappers.fromCourseToCourseEntity

import com.example.data.models.CourseEntity
import com.example.domain.Course

interface MapperFromCourseToCourseEntity {

    fun mapToCourseEntity(course: Course): CourseEntity

}