package com.example.bookkeeping.classification_add

import androidx.lifecycle.ViewModel
import com.example.bookkeeping.db.MenuDao
import com.example.bookkeeping.db.entity.Menu

/**
 * Created by 虫虫 on 2021/6/22
 */
class ClassificationAddModel : ViewModel() {

    fun toSaveData(menu: Menu, menuDao: MenuDao) {
        menuDao.addMenu(menu)
    }
}