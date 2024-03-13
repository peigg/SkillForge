package com.peigg.skillforge.data.bd.repositories

import android.content.ContentValues
import android.content.SharedPreferences
import com.peigg.skillforge.R
import com.peigg.skillforge.data.bd.room.CoachesEntity
import com.peigg.skillforge.data.bd.room.CoachesRoom
import com.peigg.skillforge.data.bd.sqlite.DbHelper
import com.peigg.skillforge.data.bd.sqlite.SkillForgeDatabase
import com.peigg.skillforge.datainjection.CoachesSharedPreferences
import com.peigg.skillforge.domain.Coaches
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SkillForgeRepository @Inject constructor(
    private val dbHelper: DbHelper,
    private val coachesRoom: CoachesRoom,
    @CoachesSharedPreferences private val coachesSharedPreferences: SharedPreferences
) {
    suspend fun setupSkillForgeDatabase() {
        withContext(IO) {
            if (coachesRoom.coachesDao().getAll().first().isEmpty()) {
                val coach = CoachesEntity(
                    1,
                    "John Doe",
                    R.drawable.image_pablo,
                    "Java",
                    "John Doe is a Java expert with 10 years of experience",
                    "100"
                )
                val coach2 = CoachesEntity(
                    2,
                    "Mark",
                    R.drawable.image_pablo,
                    "Java",
                    "Mark is a React expert with 10 years of experience",
                    "1000"
                )
                val coach3 = CoachesEntity(
                    3,
                    "Pablo",
                    R.drawable.image_pablo,
                    "Java",
                    "Pablo is a Java expert with 10 years of experience",
                    "100"
                )
                coachesRoom.coachesDao().insertCoach(coach, coach2, coach3)
            }
        }

        withContext(IO) {

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
}

    suspend fun getCoaches(): List<Coaches> =
        withContext(IO) {

            val coachesEntities = coachesRoom.coachesDao().getAll().first()
            coachesEntities.map { Coaches(it.id!!, it.name, it.image, it.spec, it.description, it.price) }

        }
    //logic to get coaches from sqlite
       /* val db = dbHelper.writableDatabase
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
    } */
}

