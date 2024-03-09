package com.peigg.skillforge.domain

import com.peigg.skillforge.data.bd.coaches_db.CoachesEntity

data class Coaches(
    val id: Int = 0,
    val name: String = "",
    val image: Int = 0,
    val spec: String = "",
    val description: String = "",
    val price: String = ""
 )

fun Coaches.toEntity() = CoachesEntity(
        id = id,
        name = name,
        image = image,
        spec = spec,
        description = description,
        price = price
    )