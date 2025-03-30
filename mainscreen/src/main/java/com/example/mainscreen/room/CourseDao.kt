package com.example.mainscreen.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.models.Course

@Dao
interface CourseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertACourse(course: Course)

    @Query("SELECT * FROM FAVORITE_COURSES")
    fun getAllCourses(): List<Course>

    @Delete
    fun deleteACourse(course: Course)

}