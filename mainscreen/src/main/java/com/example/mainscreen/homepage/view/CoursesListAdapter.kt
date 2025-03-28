package com.example.mainscreen.homepage.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.mainscreen.R
import com.example.mainscreen.homepage.model.Course
import java.text.SimpleDateFormat
import java.util.Locale

class CoursesListAdapter :
    ListAdapter<Course, CoursesListAdapter.CourseViewHolder>(CourseDiffCallBack()) {

    class CourseViewHolder(view: View) : ViewHolder(view) {
        val favoriteButton: ImageButton = view.findViewById(R.id.favoriteButton)
        val courseRate: TextView = view.findViewById(R.id.courseRateTextView)
        val courseStartDate: TextView = view.findViewById(R.id.courseStartDate)
        val courseTitle: TextView = view.findViewById(R.id.courseTitle)
        val courseText: TextView = view.findViewById(R.id.courseText)
        val coursePrice: TextView = view.findViewById(R.id.coursePrice)
        val coursePicture: ImageView = view.findViewById(R.id.courseImageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.course_item_layout, parent, false)
        return CourseViewHolder(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        val course = getItem(position)
        holder.courseText.text = course.text
        holder.coursePrice.text = "${course.price} ₽"
        holder.courseTitle.text = course.title
        holder.courseRate.text = course.rate
        holder.courseStartDate.text = course.startDate.formatDate()
        holder.favoriteButton.setImageResource(
            when (course.hasLike) {
                true -> R.drawable.favorite_icon_filled
                false -> R.drawable.favorite_icon_main_screen
            }
        )
        Glide.with(holder.itemView).load(R.drawable.cover).centerCrop().into(holder.coursePicture)
    }

    private fun String.formatDate(): String {
        return try {
            val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val outputFormat =
                SimpleDateFormat("d MMMM yyyy", Locale("ru")) // "ru" для русских названий месяцев

            val date = inputFormat.parse(this) ?: return this
            outputFormat.format(date)
        } catch (e: Exception) {
            this
        }
    }
}


