package com.example.data.mappers.fromCourseDtoToCourse

import com.example.data.models.CourseDto
import com.example.domain.Course

interface MapperFromCourseDtoToCourse {

    fun mapFromCourseDtoToCourse(courseDto: List<CourseDto>): List<Course>

}