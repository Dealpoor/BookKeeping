package com.example.book_keeping.common.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.book_keeping.common.db.entity.Menu
import com.example.book_keeping.common.db.entity.Record

/**
 * Created by 虫虫 on 2021/6/21
 * 统一管理各种表和数据库
 */
@Database(entities = [Menu::class, Record::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun menuDao(): MenuDao

    abstract fun recordDao(): RecordDao

    companion object {
//        private val MIGRATION_1_2 = object : Migration(1,2){
//            override fun migrate(database: SupportSQLiteDatabase) {
//
//            }
//        }

        private var instance: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "User.db" //数据库名称
                )/*.addMigrations(MIGRATION_1_2)*/
                    .allowMainThreadQueries().build()
            }
            return instance as AppDatabase
        }
    }
}