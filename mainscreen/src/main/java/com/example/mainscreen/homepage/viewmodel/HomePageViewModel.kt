package com.example.mainscreen.homepage.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.data.models.Course
import com.example.data.repository.AppRepository
import com.example.mainscreen.di.AppProvider
import com.example.mainscreen.room.CoursesDataBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject

class HomePageViewModel @Inject constructor(
    private val repository: AppRepository,
    private val room: CoursesDataBase
) : ViewModel() {


    private var _coursesListState = MutableStateFlow<List<Course>>(emptyList())

    val coursesListState = _coursesListState.asStateFlow()

    init {
        getResult()
    }

    private fun getResult() = runBlocking {
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
        for (item in readyList) {
            if (item.hasLike) {
                saveACourse(item)
            }
        }
        _coursesListState.value = readyList.toList()
    }

    fun saveACourse(course: Course) {
        viewModelScope.launch(Dispatchers.IO) {
            room.getCourseDao().insertACourse(course)
        }
    }

    fun deleteACourse(course: Course) {
        viewModelScope.launch(Dispatchers.IO) {
            room.getCourseDao().deleteACourse(course)
        }
    }

    fun sortItemsByDateDescending(list: List<Course>): List<Course> {
        val currentList = list.toMutableList()
        val sortedList = currentList.sortedByDescending { item ->
            SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(item.publishDate)
        }
        return sortedList
    }

    fun updateState(list: List<Course>) {
        _coursesListState.value = list
    }

    companion object {
        @Suppress("UNCHECKED_CAST")
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                val appProvider = checkNotNull(extras[APPLICATION_KEY]) as AppProvider
                return HomePageViewModel(appProvider.appRepository, appProvider.room) as T
            }
        }
    }
}