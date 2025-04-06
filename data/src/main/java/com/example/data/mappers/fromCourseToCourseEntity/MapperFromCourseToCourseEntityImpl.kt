package com.example.data.mappers.fromCourseToCourseEntity

import com.example.data.models.CourseEntity
import com.example.domain.Course

class MapperFromCourseToCourseEntityImpl : MapperFromCourseToCourseEntity {
    override fun mapToCourseEntity(course: Course) = CourseEntity(
        id = course.id,
        hasLike = course.hasLike,
        price = course.price,
        publishDate = course.publishDate,
        rate = course.rate,
        startDate = course.startDate,
        title = course.title,
        text = course.text
    )
}