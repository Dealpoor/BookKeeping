package com.example.bookkeeping.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bookkeeping.db.entity.Record
import java.util.*

/**
 * Created by 虫虫 on 2021/6/23
 */
@Dao
interface RecordDao {
    /**
     * 操作记录数据库
     */

    //查询(根据时间段去查询当前时间下的所有记录）
    @Query("select * from record where record_time >= :startTime and record_time <= :endTime")
    fun getRecordByTime(startTime: Date, endTime: Date): Record

    //插入
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addRecord(record: Record)
}