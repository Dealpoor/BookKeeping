package com.example.bookkeeping.classification

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bookkeeping.App
import com.example.bookkeeping.db.AppDatabase
import com.example.bookkeeping.db.MenuDao
import com.example.bookkeeping.db.entity.Menu
import com.example.bookkeeping.db.entity.Record

/**
 * Created by 虫虫 on 2021/6/17
 */
class ClassificationViewModel : ViewModel() {

    val itemListLiveData = MutableLiveData<List<Menu>>()

    fun getItemList(menuDao: MenuDao) {
        val list = menuDao.getMenuAll()
        itemListLiveData.postValue(list)
    }

    //加号的点击事件，主要操作有两个，第一个是把菜单里面的数量进行更改
    //第二个是把这个记录保存到本地上面去
    fun addMenuNum(menu: Menu,reocrd : Record,menuDao: MenuDao){

    }
}