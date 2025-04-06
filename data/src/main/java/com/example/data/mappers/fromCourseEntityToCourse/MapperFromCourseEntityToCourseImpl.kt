package com.example.data.mappers.fromCourseEntityToCourse

import com.example.data.models.CourseEntity
import com.example.domain.Course

class MapperFromCourseEntityToCourseImpl : MapperFromCourseEntityToCourse {

    override fun mapToCourse(courseEntity: List<CourseEntity>): List<Course> =
        courseEntity.map {
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