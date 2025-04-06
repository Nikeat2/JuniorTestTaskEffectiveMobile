package com.example.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.models.CourseEntity

@Dao
interface CourseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertACourse(course: CourseEntity)

    @Query("SELECT * FROM FAVORITE_COURSES")
    fun getFavoriteCourses(): List<CourseEntity>

    @Delete
    fun deleteACourse(courseEntity: CourseEntity)

}