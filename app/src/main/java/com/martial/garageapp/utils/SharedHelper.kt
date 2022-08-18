package com.martial.garageapp.utils

import android.content.Context
import android.content.SharedPreferences

/**
 * @Author: surajsahani
 * @Date: 18/08/22
 */
class SharedHelper(context: Context) {
    private val sharedPref: SharedPreferences = context.getSharedPreferences(Constant.PREF,  Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = sharedPref.edit()

    fun put(key: String, value: String) {
        editor.putString(key, value).apply()
    }

    fun put(key: String ,value: Boolean) {
        editor.putBoolean(key, value).apply()
    }

    fun getBoolean(key: String, value: Boolean): Boolean {
        return sharedPref.getBoolean(key, value)
    }

    fun clear() {
        editor.clear()
            .apply()
    }
}