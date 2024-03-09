package com.peigg.skillforge.data.bd.sqlite



import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class DbHelper( context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){


    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_ENTRIES)

    }
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }




    companion object {

        // If you change the database schema, you must increment the database version.
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "SkillForgeDatabase.db"
        private const val SQL_CREATE_ENTRIES=
            """
            CREATE TABLE ${SkillForgeDatabase.TABLE_NAME}
                (
                ${SkillForgeDatabase.COLUMN_ID} INTEGER PRIMARY KEY AUTOINCREMENT,
                ${SkillForgeDatabase.COLUMN_NAME} TEXT,
                ${SkillForgeDatabase.COLUMN_IMAGE} INTEGER,
                ${SkillForgeDatabase.COLUMN_SPEC} TEXT,
                ${SkillForgeDatabase.COLUMN_DESCRIPTION} TEXT,
                ${SkillForgeDatabase.COLUMN_PRICE} TEXT
                )
            """

    }
}