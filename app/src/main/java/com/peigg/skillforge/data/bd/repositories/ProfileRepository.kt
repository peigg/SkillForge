package com.peigg.skillforge.data.bd.repositories

import android.content.SharedPreferences
import com.peigg.skillforge.datainjection.SettingsSharedPreferences
import javax.inject.Inject

class ProfileRepository @Inject constructor(
    @SettingsSharedPreferences private val sharedPrefs: SharedPreferences
){
    fun saveProfile(profile: String, isCheched: Boolean)
    {
        sharedPrefs.edit().putBoolean(profile, isCheched).apply()
    }

    fun getProfile(profile: String): Boolean
    {
        return sharedPrefs.getBoolean(profile, false)
    }
}