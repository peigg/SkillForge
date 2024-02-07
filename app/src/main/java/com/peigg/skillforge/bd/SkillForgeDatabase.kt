package com.peigg.skillforge.bd

object SkillForgeDatabase {

    const val TABLE_NAME = "coaches"
    const val COLUMN_NAME = "name"
    const val COLUMN_IMAGE = "image"
    const val COLUMN_SPEC = "spec"
    const val COLUMN_DESCRIPTION = "description"
    const val COLUMN_PRICE = "price"


}

private const val SQL_CREATE_ENTRIES = """
    "CREATE TABLE ${SkillForgeDatabase.TABLE_NAME} (" +
            "${SkillForgeDatabase.COLUMN_NAME} TEXT PRIMARY KEY," +
            "${SkillForgeDatabase.COLUMN_IMAGE} INTEGER," +
            "${SkillForgeDatabase.COLUMN_SPEC} TEXT," +
            "${SkillForgeDatabase.COLUMN_DESCRIPTION} TEXT," +
            "${SkillForgeDatabase.COLUMN_PRICE} TEXT)"""

private const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${SkillForgeDatabase.TABLE_NAME}"