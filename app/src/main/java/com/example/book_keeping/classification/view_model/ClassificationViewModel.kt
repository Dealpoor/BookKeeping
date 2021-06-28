package com.example.book_keeping.classification.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.book_keeping.classification.model.MenuDao
import com.example.book_keeping.classification.model.Menu

/**
 * Created by 虫虫 on 2021/6/17
 * 操作商品数据库
 */
class ClassificationViewModel : ViewModel() {

    val itemListLiveData = MutableLiveData<List<Menu>>()

    fun getItemList(menuDao: MenuDao) {
        val list = menuDao.getMenuAll()
        itemListLiveData.postValue(list)
    }
}