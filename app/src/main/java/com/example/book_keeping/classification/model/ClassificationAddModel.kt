package com.example.book_keeping.classification.model

import androidx.lifecycle.ViewModel
import com.example.book_keeping.common.db.MenuDao
import com.example.book_keeping.common.db.entity.Menu

/**
 * Created by 虫虫 on 2021/6/22
 */
class ClassificationAddModel : ViewModel() {

    fun toSaveData(menu: Menu, menuDao: MenuDao) {
        menuDao.addMenu(menu)
    }
}