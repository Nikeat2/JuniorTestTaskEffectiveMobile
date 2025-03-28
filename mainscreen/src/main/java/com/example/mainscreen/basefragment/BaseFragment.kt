package com.example.mainscreen.basefragment

import androidx.fragment.app.Fragment
import com.example.mainscreen.homepage.model.BottomNavigationController

abstract class BaseFragment : Fragment() {
    protected val navigationController: BottomNavigationController?
        get() = activity as? BottomNavigationController

    override fun onResume() {
        super.onResume()
        navigationController?.showBottomNavigation(shouldShowBottomNav())
    }

    abstract fun shouldShowBottomNav(): Boolean
}