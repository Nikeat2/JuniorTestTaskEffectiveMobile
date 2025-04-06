package com.example.data.mappers.fromCourseDtoToCourse

import com.example.data.models.CourseDto
import com.example.domain.Course

class MapperFromCourseDtoToCourseImpl : MapperFromCourseDtoToCourse {
    override fun mapFromCourseDtoToCourse(courseDto: List<CourseDto>): List<Course> =
        courseDto.map {
            Course(
                hasLike = it.hasLike,
                id = it.id,
                price = it.price,
                publishDate = it.publishDate,
                rate = it.rate,
                startDate = it.startDate,
                text = it.text,
                title = it.title
            )
        }

}