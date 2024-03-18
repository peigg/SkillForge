package com.peigg.skillforge.data.bd.repositories

import android.content.SharedPreferences
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import com.peigg.skillforge.datainjection.SettingsSharedPreferences
import com.peigg.skillforge.features.ui.profileScreen.ProfileEvent
import com.peigg.skillforge.features.ui.profileScreen.ProfileEvent.*
import com.peigg.skillforge.features.ui.profileScreen.ProfileState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class ProfileRepository @Inject constructor(
    @SettingsSharedPreferences private val sharedPrefs: SharedPreferences,
    private val profileDataStore: DataStore<Preferences>
){
    fun saveProfile(profile: String, isChecked: Boolean)
    {
        sharedPrefs.edit().putBoolean(profile, isChecked).apply()
        runBlocking {
            profileDataStore.edit { preferences ->
                preferences[booleanPreferencesKey(profile)] = isChecked
        }

        }
    }

    fun getProfile(profile: String): Boolean
    {
        return sharedPrefs.getBoolean(profile, false)
    }


    fun getProfileDataStore() : Flow<Map<ProfileEvent, Boolean>> = profileDataStore.data.map { preferences ->

        mapOf<ProfileEvent,Boolean>(
            Profile to (preferences[booleanPreferencesKey(Profile.name)]?: false),
            Notifications to (preferences[booleanPreferencesKey(Notifications.name)]?: false),
            Name to (preferences[booleanPreferencesKey(Name.name)]?: false),
            Email to (preferences[booleanPreferencesKey(Email.name)]?: false),
            Phone to (preferences[booleanPreferencesKey(Phone.name)]?: false)


        )
    }

}