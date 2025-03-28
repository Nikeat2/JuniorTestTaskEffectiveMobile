package com.example.mainscreen.homepage.viewmodel

import androidx.lifecycle.ViewModel
import com.example.domain.AppRepositoryImpl
import com.example.mainscreen.homepage.model.Course
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.runBlocking
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject

class HomePageViewModel @Inject constructor() : ViewModel() {

    private var _coursesListState = MutableStateFlow<List<Course>>(emptyList())

    val coursesListState = _coursesListState.asStateFlow()

    fun getResult(repository: AppRepositoryImpl) = runBlocking {
        val res = async { repository.getAllCourses() }

        val newList = res.await()
        val readyList = newList.courses.map {
            Course(
                hasLike = it.hasLike,
                id = it.id,
                price = it.price,
                publishDate = it.publishDate,
                rate = it.rate,
                startDate = it.startDate,
                text = it.text,
                title = it.title
            )
        }
        _coursesListState.value = readyList.toList()
    }

     fun sortItemsByDateDescending(list: List<Course>) : List<Course> {
        val currentList = list.toMutableList()
        val sortedList = currentList.sortedByDescending { item ->
            SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(item.publishDate)
        }
        return sortedList
    }
}