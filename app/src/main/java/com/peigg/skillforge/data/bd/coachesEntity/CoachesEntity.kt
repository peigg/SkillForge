package com.peigg.skillforge.data.bd.coachesEntity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.peigg.skillforge.domain.Coaches

@Entity
class CoachesEntity (
   @PrimaryKey val id: Int,
   @ColumnInfo val name: String,
   @ColumnInfo  val image: Int,
   @ColumnInfo val spec: String,
   @ColumnInfo val description: String,
   @ColumnInfo val price: String
)

fun CoachesEntity.toDomain() = Coaches(
   id = id,
   name = name,
   image = image,
   spec = spec,
   description = description,
   price = price
)



