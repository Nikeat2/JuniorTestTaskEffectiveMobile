package com.example.mainscreen.homepage.view

import androidx.recyclerview.widget.DiffUtil
import com.example.mainscreen.homepage.model.Course

class CourseDiffCallBack : DiffUtil.ItemCallback<Course>() {
    override fun areItemsTheSame(oldItem: Course, newItem: Course): Boolean = oldItem.id == newItem.id


    override fun areContentsTheSame(oldItem: Course, newItem: Course): Boolean = oldItem == newItem

}
