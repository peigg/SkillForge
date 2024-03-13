package com.peigg.skillforge.datainjection

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class CoachesSharedPreferences

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class SettingsSharedPreferences
@Module
@InstallIn(SingletonComponent::class)
object SharedPreferencesModule {
    @CoachesSharedPreferences
    @Provides
    fun provideCoachesSharedPreferences(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences("coaches", Context.MODE_PRIVATE)

    @SettingsSharedPreferences
    @Provides
    fun provideSettingsSharedPreferences(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences("settings", Context.MODE_PRIVATE)
}

