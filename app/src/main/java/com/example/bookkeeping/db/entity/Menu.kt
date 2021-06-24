package com.example.bookkeeping.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by 虫虫 on 2021/6/21
 */
@Entity(tableName = "menu")
data class Menu(
    @PrimaryKey(autoGenerate = true) var menuId: Long,
    @ColumnInfo(name = "menu_name") var menuName: String,
    @ColumnInfo(name = "menu_num") var menuNum: Long
)