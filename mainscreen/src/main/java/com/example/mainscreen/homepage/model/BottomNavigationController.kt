package com.example.mainscreen.homepage.model

import androidx.fragment.app.Fragment

interface BottomNavigationController {
    fun showBottomNavigation(show: Boolean)
    fun navigateTo(fragment: Fragment)
    fun setSelectedItem(itemId: Int)
}