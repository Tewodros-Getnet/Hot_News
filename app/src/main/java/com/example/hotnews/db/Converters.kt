package com.example.hotnews.db

import androidx.room.TypeConverter
import com.example.hotnews.models.Source

class Converters {
    @TypeConverter
    fun fromSource(source: Source): String {
        return source.name
    }

    @TypeConverter
    fun toSource(name: String): Source {
        return Source(name, name)  // Note: Verify Source constructor accepts two strings
    }
}