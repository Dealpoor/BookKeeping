package com.example.book_keeping.classification.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.book_keeping.common.db.MenuDao
import com.example.book_keeping.common.db.entity.Menu
import com.example.book_keeping.common.db.entity.Record

/**
 * Created by 虫虫 on 2021/6/17
 */
class ClassificationViewModel : ViewModel() {

    val itemListLiveData = MutableLiveData<List<Menu>>()

    fun getItemList(menuDao: MenuDao) {
        val list = menuDao.getMenuAll()
        itemListLiveData.postValue(list)
    }
}