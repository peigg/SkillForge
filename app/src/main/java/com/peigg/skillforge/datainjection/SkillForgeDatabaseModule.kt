package com.peigg.skillforge.datainjection

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.peigg.skillforge.data.bd.DbHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ViewModelComponent::class)

object SkillForgeDatabaseModule {
    const val DATABASE_NAME = "SkillForgeDatabase.db"

@Provides

fun providesDbHelper(@ApplicationContext context: Context) : SQLiteDatabase { return DbHelper(context).readableDatabase }

}