package com.example.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.models.CourseEntity

@Database(
    entities = [CourseEntity::class],
    version = 1
)
abstract class CoursesDataBase : RoomDatabase() {
    abstract fun getCourseDao(): CourseDao
}