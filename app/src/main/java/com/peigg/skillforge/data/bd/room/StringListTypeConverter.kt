package com.peigg.skillforge.data.bd.room

import androidx.room.TypeConverter

// This class is used to convert a list of strings to a single string and vice versa to store it in the database,
// no needed in the current project but it's a good practice to have it
class StringListTypeConverter {
    @TypeConverter
    fun fromString(value: String): List<String> {
        return value.split(",")
    }

    @TypeConverter
    fun fromList(list: List<String>): String {
        return list.joinToString(",")
    }
}