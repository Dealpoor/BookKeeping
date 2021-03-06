package com.example.book_keeping.record.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * Created by 虫虫 on 2021/6/23
 * 操作记录数据库
 */
@Dao
interface RecordDao {

    //查询所有
    @Query("select * from record order by record_time desc")
    fun getAllRecord(): List<Record>

    //查询(根据时间段去查询当前时间下的所有记录）
    @Query("SELECT * FROM record WHERE record_time >= :startTime AND record_time <= :endTime")
    fun getRecordByTime(startTime: Long, endTime: Long): List<Record>

    //插入
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addRecord(record: Record)
}