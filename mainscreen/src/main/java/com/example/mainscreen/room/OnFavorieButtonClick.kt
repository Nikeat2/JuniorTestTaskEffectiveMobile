package com.example.mainscreen.room

import com.example.data.models.Course

interface OnFavoriteButtonClick {

    fun onButtonClick(course: Course, position: Int)

}