package com.peigg.skillforge.data.bd.repositories

import android.content.SharedPreferences
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.peigg.skillforge.api.CatsApi
import com.peigg.skillforge.api.FactResponse
import com.peigg.skillforge.datainjection.SettingsSharedPreferences
import com.peigg.skillforge.features.ui.profileScreen.ProfileEvent
import com.peigg.skillforge.features.ui.profileScreen.ProfileEvent.*
import com.peigg.skillforge.features.ui.profileScreen.ProfileState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import okhttp3.Dispatcher
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttp
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.await
import retrofit2.converter.gson.GsonConverterFactory
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

    suspend fun getCatsFact () {
        withContext(Dispatchers.IO) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

            val client = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl("https://cat-fact.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                //.addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
                .client(client)
                .build()

            val api = retrofit.create(CatsApi::class.java)
            val fact: FactResponse = api.getCatFact().await()
            Log.d("ProfileRepository", "getCatsFact: $fact")
        }
    }

}