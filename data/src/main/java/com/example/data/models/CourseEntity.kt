package com.example.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

const val FAVORITE_COURSES = "FAVORITE_COURSES"

@Entity(tableName = FAVORITE_COURSES)
data class CourseEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val text: String,
    val price: String,
    val rate: String,
    val startDate: String,
    var hasLike: Boolean,
    val publishDate: String
)
