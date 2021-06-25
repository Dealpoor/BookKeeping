package com.example.book_keeping.common.db

import androidx.room.TypeConverter
import java.sql.Date

/**
 * Created by 虫虫 on 2021/6/25
 */
class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }
}