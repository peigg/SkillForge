package com.peigg.skillforge.datainjection


import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.peigg.skillforge.data.bd.SkillForgeRepository
import com.peigg.skillforge.data.bd.DbHelper

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SkillForgeDatabaseModule {
    const val DATABASE_NAME = "SkillForgeDatabase.db"

    @Singleton
    @Provides
    fun provideDbHelper(@ApplicationContext context: Context): DbHelper {
        return DbHelper(context)
    }

    @Singleton
    @Provides
    fun provideReadableDatabase(dbHelper: DbHelper): SQLiteDatabase {
        return dbHelper.readableDatabase
    }

    @Singleton
    @Provides
    fun provideWritableDatabase(dbHelper: DbHelper): SQLiteDatabase {
        return dbHelper.writableDatabase
    }

    @Singleton
    @Provides
    fun provideSkillForgeRepository(dbHelper: DbHelper): SkillForgeRepository {
        return SkillForgeRepository(dbHelper)
    }
}