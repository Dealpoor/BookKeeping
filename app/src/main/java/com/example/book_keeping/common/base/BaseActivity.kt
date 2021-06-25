package com.example.book_keeping.common.base

import android.annotation.TargetApi
import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.book_keeping.R
import com.example.book_keeping.common.utils.SystemBarTintManager

/**
 * Created by 虫虫 on 2021/6/17
 */
abstract class BaseActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(initLayout())
        initView()
        initData()
    }

    //设置布局
    abstract fun initLayout() : Int

    //初始化布局
    abstract fun initView()

    //初始化数据
    abstract fun initData()
}