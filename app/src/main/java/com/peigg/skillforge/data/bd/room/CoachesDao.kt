package com.peigg.skillforge.data.bd.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CoachesDao {
    @Query("SELECT * FROM coaches")
    fun getAll(): Flow<List<CoachesEntity>>

    @Insert
    fun insert(coaches: CoachesEntity)
}