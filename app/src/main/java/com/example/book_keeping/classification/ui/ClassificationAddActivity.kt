package com.example.book_keeping.classification.ui

import android.widget.Toast
import androidx.activity.viewModels
import com.example.book_keeping.R
import com.example.book_keeping.classification.model.ClassificationAddViewModel
import com.example.book_keeping.common.base.BaseActivity
import com.example.book_keeping.common.db.AppDatabase
import com.example.book_keeping.common.db.MenuDao
import com.example.book_keeping.common.db.entity.Menu
import kotlinx.android.synthetic.main.classification_activity_add_layout.*
import kotlinx.android.synthetic.main.common_title_layout.*

/**
 * 添加商品页面
 */
class ClassificationAddActivity : BaseActivity() {

    private lateinit var menuDao: MenuDao

    private val mViewViewModel: ClassificationAddViewModel by viewModels()

    override fun initLayout() = R.layout.classification_activity_add_layout

    override fun initView() {
        menuDao = AppDatabase.getInstance(this).menuDao()
        //设置标题文字
        title_name.text = getString(R.string.classification_activity_add_title)
    }

    override fun initData() {
        btn_save.setOnClickListener {
            if (et_name.text.toString().trim().isEmpty()) {
                Toast.makeText(
                    this,
                    R.string.classification_activity_add_toast_menu_name,
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            if (et_num.text.toString().trim().isEmpty()) {
                Toast.makeText(
                    this,
                    R.string.classification_activity_add_toast_menu_num,
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            val menu = Menu(
                0,
                et_name.text.toString().trim(),
                et_num.text.toString().trim().toLong()
            )
            mViewViewModel.toSaveData(menu, menuDao)
            finish()
        }
    }
}