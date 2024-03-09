package com.peigg.skillforge.data.bd.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CoachesEntity::class], version = 1)
 abstract class CoachesRoom : RoomDatabase() {
    abstract fun coachesDao(): CoachesDao

}