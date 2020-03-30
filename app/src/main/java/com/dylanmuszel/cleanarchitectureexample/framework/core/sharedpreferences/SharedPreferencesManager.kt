package com.dylanmuszel.cleanarchitectureexample.framework.core.sharedpreferences

import android.content.SharedPreferences
import androidx.core.content.edit
import com.google.gson.GsonBuilder
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPreferencesManager @Inject constructor(val sharedPreferences: SharedPreferences) {

    fun <T> put(key: String, `object`: T) {
        val jsonString = GsonBuilder().create().toJson(`object`)
        sharedPreferences.edit { putString(key, jsonString) }
    }

    inline operator fun <reified T> get(key: String): T {
        val value = sharedPreferences.getString(key, null)
        return GsonBuilder().create().fromJson(value, T::class.java)
    }
}