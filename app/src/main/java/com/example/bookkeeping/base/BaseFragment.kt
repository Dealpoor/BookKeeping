package com.example.bookkeeping.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

/**
 * Created by 虫虫 on 2021/6/17
 */
abstract class BaseFragment : Fragment(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //用来设置intent跳转的，registerForActivityResult 如果从fragment跳转到activity
        //就必须在fragment的onCreate里面进行回调的编写
        thisIntent()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var rootView = inflater.inflate(initLayout(),container,false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initData()
    }

    //设置跳转回调
    abstract fun thisIntent()

    //初始化布局
    abstract fun initLayout() : Int

    //初始化视图
    abstract fun initView()

    //初始化数据
    abstract fun initData()
}