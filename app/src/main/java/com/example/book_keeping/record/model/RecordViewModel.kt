package com.example.book_keeping.record.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.book_keeping.common.db.RecordDao
import com.example.book_keeping.common.db.entity.Record
import java.util.*

/**
 * Created by 虫虫 on 2021/6/25
 */
class RecordViewModel : ViewModel() {

    val itemListLiveData = MutableLiveData<List<Record>>()

    fun getItemList(recordDao: RecordDao) {
        val list = recordDao.getAllRecord()
        itemListLiveData.postValue(list)
    }

    fun getItemByTime(startTime: Date, endTime: Date, recordDao: RecordDao) {
        val list = recordDao.getRecordByTime(startTime,endTime)
        itemListLiveData.postValue(list)
    }
}