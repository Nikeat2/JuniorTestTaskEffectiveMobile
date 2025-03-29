package com.example.mainscreen.favoritepage.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.data.models.Course
import com.example.mainscreen.di.AppProvider
import com.example.mainscreen.room.CoursesDataBase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoriteCoursesViewModel @Inject constructor(
    private val room: CoursesDataBase
) : ViewModel() {


    private var _coursesListState = MutableStateFlow<List<Course>>(emptyList())


    init {
        getLists()
    }

    val coursesListState = _coursesListState.asStateFlow()

    fun getLists() {
        viewModelScope.launch(Dispatchers.IO) {
            _coursesListState.value = room.getCourseDao().getAllCourses()
        }
    }

    fun deleteACourse(course: Course) {
        viewModelScope.launch(Dispatchers.IO) {
            room.getCourseDao().deleteACourse(course)
        }
    }



    companion object {
        @Suppress("UNCHECKED_CAST")
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                val appProvider = checkNotNull(extras[APPLICATION_KEY]) as AppProvider
                return FavoriteCoursesViewModel(appProvider.room) as T
            }
        }
    }
}