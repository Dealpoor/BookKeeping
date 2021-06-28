package com.example.book_keeping.record.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

/**
 * Created by 虫虫 on 2021/6/23
 * 记录表，用来记录用户操作
 */
@Entity(tableName = "record")
data class Record(
    @PrimaryKey(autoGenerate = true) var recordId: Long,
    @ColumnInfo(name = "menu_id") var menuId: Long,
    @ColumnInfo(name = "record_item") var recordItem: String,
    @ColumnInfo(name = "record_time") var recordTime: Long
)