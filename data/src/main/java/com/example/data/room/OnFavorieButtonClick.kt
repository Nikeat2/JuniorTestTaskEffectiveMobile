package com.example.data.room

import com.example.domain.Course

interface OnFavoriteButtonClick {

    fun onButtonClick(courseEntity: Course, position: Int)

}