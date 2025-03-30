package com.example.data.firstusage

import android.content.Context
import android.content.SharedPreferences

class AppPreferences(private val context: Context) {


    val sharedPrefs: SharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)

    companion object {
        private const val KEY_FIRST_LAUNCH = "is_first_launch"
    }

    fun isFirstLaunch(): Boolean {
        return sharedPrefs.getBoolean(KEY_FIRST_LAUNCH, true)
    }

    fun setLaunched() {
        sharedPrefs.edit().putBoolean(KEY_FIRST_LAUNCH, false).apply()
    }

    fun clearPreferences() {
        sharedPrefs.edit().clear().apply()
    }

}