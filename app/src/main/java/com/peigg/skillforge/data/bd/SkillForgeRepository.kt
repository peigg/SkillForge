package com.peigg.skillforge.data.bd

import android.content.ContentValues
import com.peigg.skillforge.R
import com.peigg.skillforge.domain.Coaches
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SkillForgeRepository @Inject constructor(private val dbHelper: DbHelper) {

    suspend fun setupSkillForgeDatabase() = withContext(IO) {
        val db = dbHelper.writableDatabase // Obtén la base de datos para operaciones de escritura
        val cursor = db.rawQuery("SELECT COUNT(*) FROM ${SkillForgeDatabase.TABLE_NAME}", null)
        cursor.moveToFirst()
        val count = cursor.getInt(0)
        cursor.close()

        if (count <= 0) {
            val imageResId = R.drawable.image_pablo // Asegúrate de que este recurso exista

            val values = ContentValues().apply {
                put(SkillForgeDatabase.COLUMN_NAME, "John Doe")
                put(SkillForgeDatabase.COLUMN_IMAGE, imageResId)
                put(SkillForgeDatabase.COLUMN_SPEC, "Java")
                put(SkillForgeDatabase.COLUMN_DESCRIPTION, "John Doe is a Java expert with 10 years of experience")
                put(SkillForgeDatabase.COLUMN_PRICE, "100")
            }

            db.insert(SkillForgeDatabase.TABLE_NAME, null, values)
        }
    }

    suspend fun getCoaches(): List<Coaches> = withContext(IO) {
        val db = dbHelper.writableDatabase
        val cursor = db.query(
            SkillForgeDatabase.TABLE_NAME,
            null, // All columns
            null, // No WHERE conditions
            null, // No values for WHERE conditions
            null, // groupBy
            null, // having
            null  // orderBy
        )

        val list = mutableListOf<Coaches>()
        while (cursor.moveToNext()) {
            val id = cursor.getInt(cursor.getColumnIndexOrThrow(SkillForgeDatabase.COLUMN_ID))
            val name = cursor.getString(cursor.getColumnIndexOrThrow(SkillForgeDatabase.COLUMN_NAME))
            val image = cursor.getInt(cursor.getColumnIndexOrThrow(SkillForgeDatabase.COLUMN_IMAGE))
            val spec = cursor.getString(cursor.getColumnIndexOrThrow(SkillForgeDatabase.COLUMN_SPEC))
            val description = cursor.getString(cursor.getColumnIndexOrThrow(SkillForgeDatabase.COLUMN_DESCRIPTION))
            val price = cursor.getString(cursor.getColumnIndexOrThrow(SkillForgeDatabase.COLUMN_PRICE))

            list.add(Coaches(id, name, image, spec, description, price))
        }
        cursor.close()
        list
    }

}
