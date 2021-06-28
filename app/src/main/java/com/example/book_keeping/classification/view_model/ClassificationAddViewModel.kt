package com.example.book_keeping.classification.view_model

import androidx.lifecycle.ViewModel
import com.example.book_keeping.classification.model.MenuDao
import com.example.book_keeping.classification.model.Menu

/**
 * Created by 虫虫 on 2021/6/22
 * 添加商品页面的数据库操作类
 */
class ClassificationAddViewModel : ViewModel() {

    fun toSaveData(menu: Menu, menuDao: MenuDao) {
        menuDao.addMenu(menu)
    }
}