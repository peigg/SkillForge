package com.peigg.skillforge.data.bd.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "coaches")

data class CoachesEntity(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val name: String,
    val image: Int,
    val spec: String,
    val description: String,
    val price: String
)

