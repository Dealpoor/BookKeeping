package com.example.bookkeeping.db

import androidx.room.*
import com.example.bookkeeping.db.entity.Menu
import com.example.bookkeeping.db.entity.Record
import java.util.*

/**
 * Created by 虫虫 on 2021/6/21
 */
@Dao
interface MenuDao {
    //查询
    @Query("select * from menu where menuId = :id")
    fun getMenuById(id: Long): Menu

    //查询Menu所有数据
    @Query("select * from menu")
    fun getMenuAll(): List<Menu>

    //插入
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addMenu(menu: Menu)

    //删除
    @Delete
    fun deleteMenuByMenu(menu: Menu)

    //修改菜单数据库的数量
    @Query("update menu set menu_num = :menuNum where menuId =  :id")
    fun updateMenuNum(id: Long, menuNum: Long)

}