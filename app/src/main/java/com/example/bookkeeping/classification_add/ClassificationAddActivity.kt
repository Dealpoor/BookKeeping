package com.example.bookkeeping.classification_add

import android.widget.Toast
import androidx.activity.viewModels
import com.example.bookkeeping.R
import com.example.bookkeeping.base.BaseActivity
import com.example.bookkeeping.db.AppDatabase
import com.example.bookkeeping.db.MenuDao
import com.example.bookkeeping.db.entity.Menu
import kotlinx.android.synthetic.main.activity_classification_add.*

class ClassificationAddActivity : BaseActivity() {

    private lateinit var menuDao: MenuDao

    private val mViewModel: ClassificationAddModel by viewModels()

    override fun initLayout() = R.layout.activity_classification_add

    override fun initView() {
        menuDao = AppDatabase.getInstance(this).menuDao()
    }

    override fun initData() {
        btn_save.setOnClickListener {
            if ("" == et_name.text.toString().trim()) {
                Toast.makeText(this, R.string.menu_name_not_null, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if ("" == et_num.text.toString().trim()) {
                Toast.makeText(this, R.string.menu_num_not_null, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val menu = Menu(
                0,
                et_name.text.toString().trim(),
                et_num.text.toString().trim().toLong()
            )
            mViewModel.toSaveData(menu, menuDao)
            finish()
        }
    }
}