package com.example.bookkeeping.base

import android.annotation.TargetApi
import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.bookkeeping.R
import com.example.bookkeeping.utils.SystemBarTintManager

/**
 * Created by 虫虫 on 2021/6/17
 */
abstract class BaseActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(initLayout())
        initView()
        initData()
        steepStatusBar()
    }

    //设置布局
    abstract fun initLayout() : Int

    //初始化布局
    abstract fun initView()

    //初始化数据
    abstract fun initData()

    private fun steepStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true)
            val tintManager = SystemBarTintManager(this)
            tintManager.setStatusBarTintEnabled(true)
            tintManager.setStatusBarTintResource(R.color.select) //通知栏所需颜色
        }

    }

    @TargetApi(19)
    private fun setTranslucentStatus(on: Boolean) {
        val win = window
        val winParams = win.attributes
        val bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
        if (on) {
            winParams.flags = winParams.flags or bits
        } else {
            winParams.flags = winParams.flags and bits.inv()
        }
        win.attributes = winParams
    }
}