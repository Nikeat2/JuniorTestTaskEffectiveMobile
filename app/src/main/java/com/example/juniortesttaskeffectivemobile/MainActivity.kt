package com.example.juniortesttaskeffectivemobile

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.juniortesttaskeffectivemobile.databinding.ActivityMainBinding
import com.example.mainscreen.favoritepage.view.FavoriteCoursesFragment
import com.example.mainscreen.homepage.model.BottomNavigationController
import com.example.mainscreen.homepage.view.MainScreenFragment
import com.example.onboarding.OnBoardingFragment

class MainActivity : AppCompatActivity(), BottomNavigationController {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setUpBottomNavigation()
        supportFragmentManager.beginTransaction()
            .replace(R.id.main, OnBoardingFragment.newInstance(R.id.main))
            .addToBackStack("Activity").commit()
    }

    private fun setUpBottomNavigation() {
        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigationPointHome -> {
                    item.icon?.setTint(resources.getColor(R.color.green))
                    item.title
                    navigateTo(MainScreenFragment.newInstance())
                    true
                }

                R.id.navigationPointFavorites -> {
                    navigateTo(FavoriteCoursesFragment.newInstance())
                    true
                }

                R.id.navigationPointAccount -> true
                else -> {
                    false
                }
            }

        }
    }

    override fun showBottomNavigation(show: Boolean) {
        binding.bottomNavigationView.visibility = View.VISIBLE
    }

    override fun navigateTo(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.main, fragment).commit()
    }

    override fun setSelectedItem(itemId: Int) {
        binding.bottomNavigationView.selectedItemId = itemId
    }

}