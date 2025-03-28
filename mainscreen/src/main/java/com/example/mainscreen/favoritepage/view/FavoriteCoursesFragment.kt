package com.example.mainscreen.favoritepage.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mainscreen.R

class FavoriteCoursesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorite_courses, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() = FavoriteCoursesFragment()
    }
}