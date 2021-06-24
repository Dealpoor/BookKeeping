package com.example.book_keeping.common.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.book_keeping.common.db.entity.Menu
import com.example.book_keeping.common.db.entity.Record

/**
 * Created by 虫虫 on 2021/6/21
 */
@Database(entities = [Menu::class, Record::class], version = 2)
abstract class AppDatabase : RoomDatabase() {

    abstract fun menuDao(): MenuDao

    abstract fun recordDao(): RecordDao

    companion object {
        private var instance: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "User.db" //数据库名称
                ).allowMainThreadQueries().build()
            }
            return instance as AppDatabase
        }
    }


}