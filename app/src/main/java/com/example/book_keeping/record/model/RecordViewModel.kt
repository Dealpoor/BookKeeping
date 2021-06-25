package com.example.book_keeping.record.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.book_keeping.common.db.RecordDao
import com.example.book_keeping.common.db.entity.Record

/**
 * Created by 虫虫 on 2021/6/25
 */
class RecordViewModel : ViewModel() {

    val itemListLiveData = MutableLiveData<List<Record>>()

    //查询record表中的所有数据
    fun getItemList(recordDao: RecordDao) {
        val list = recordDao.getAllRecord()
        itemListLiveData.postValue(list)
    }

    //根据时间戳去查询表中的数据
    fun getItemByTime(startTime: Long, endTime: Long, recordDao: RecordDao) {
        val list = recordDao.getRecordByTime(startTime, endTime)
        itemListLiveData.postValue(list)
    }
}