package com.example.mainscreen.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.models.Course
import javax.inject.Inject

@Database(
    entities = [Course::class],
    version = 1
)
abstract class CoursesDataBase : RoomDatabase() {
    abstract fun getCourseDao(): CourseDao
}