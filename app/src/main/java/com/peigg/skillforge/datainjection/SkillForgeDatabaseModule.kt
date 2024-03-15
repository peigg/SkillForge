package com.peigg.skillforge.datainjection


import android.content.Context
import android.content.SharedPreferences
import android.database.sqlite.SQLiteDatabase
import androidx.room.Room
import com.peigg.skillforge.data.bd.repositories.SkillForgeRepository
import com.peigg.skillforge.data.bd.room.CoachesRoom
import com.peigg.skillforge.data.bd.sqlite.DbHelper

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object SkillForgeDatabaseModule {
    const val DATABASE_NAME = "SkillForgeDatabase.db"


    @Provides
    fun provideDbHelper(@ApplicationContext context: Context): DbHelper {
        return DbHelper(context)
    }


    @Provides
    fun provideReadableDatabase(dbHelper: DbHelper): SQLiteDatabase {
        return dbHelper.readableDatabase
    }


    @Provides
    fun provideWritableDatabase(dbHelper: DbHelper): SQLiteDatabase {
        return dbHelper.writableDatabase
    }



    @Provides
    fun provideSkillForgeRepository(
        dbHelper: DbHelper,
        coachesRoom: CoachesRoom,
        @CoachesSharedPreferences sharedPreferences: SharedPreferences
    ): SkillForgeRepository {
        return SkillForgeRepository(dbHelper, coachesRoom)
    }


    @Provides
    fun provideCoachesRoom(@ApplicationContext context: Context): CoachesRoom =
    Room.databaseBuilder(
        context, CoachesRoom::class.java,
        "CoachesRoom"
    ).build()

}
