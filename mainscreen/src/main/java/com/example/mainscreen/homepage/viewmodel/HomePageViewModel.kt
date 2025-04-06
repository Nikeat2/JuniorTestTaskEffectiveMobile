package com.example.mainscreen.homepage.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.domain.AppRepository
import com.example.domain.Course
import com.example.mainscreen.di.AppProvider
import com.example.domain.FavoriteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject

class HomePageViewModel @Inject constructor(
    private val repository: AppRepository,
    private val favoriteRepository: FavoriteRepository
) : ViewModel() {
    private var isInitialized = false

    private var _coursesListState = MutableStateFlow<List<Course>>(emptyList())

    init {
        if (!isInitialized) {
            getResult()
            isInitialized = true
        }
    }

    val coursesListState = _coursesListState.asStateFlow()

    private fun getResult() = viewModelScope.launch {
        val res = async { repository.getAllCourses() }

        val newList = res.await()
        for (item in newList) {
            if (item.hasLike) {
                saveACourse(item)
            }
        }
        _coursesListState.value = newList.toList()
    }


    fun saveACourse(course: Course) {
        viewModelScope.launch(Dispatchers.IO) {
            favoriteRepository.addACourse(course)
        }
    }

    fun deleteACourse(course: Course) {
        viewModelScope.launch(Dispatchers.IO) {
            favoriteRepository.deleteACourse(course)
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
                return HomePageViewModel(appProvider.appRepository, appProvider.favoriteRepository) as T
            }
        }
    }
}