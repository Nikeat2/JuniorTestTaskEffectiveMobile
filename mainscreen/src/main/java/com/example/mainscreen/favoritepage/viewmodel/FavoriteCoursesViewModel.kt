package com.example.mainscreen.favoritepage.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.domain.Course
import com.example.domain.FavoriteRepository
import com.example.mainscreen.di.AppProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoriteCoursesViewModel @Inject constructor(
    private val favoriteRepository: FavoriteRepository
) : ViewModel() {
    private var _coursesListState = MutableStateFlow<List<Course>>(emptyList())

    init {
        getLists()
    }

    val coursesListState = _coursesListState.asStateFlow()

    fun getLists() {
        viewModelScope.launch(Dispatchers.IO) {
            _coursesListState.value = favoriteRepository.getFavoriteCourses()
        }
    }

    fun deleteACourse(course: Course) {
        viewModelScope.launch(Dispatchers.IO) {
            favoriteRepository.deleteACourse(course)
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
                return FavoriteCoursesViewModel(appProvider.favoriteRepository) as T
            }
        }
    }
}