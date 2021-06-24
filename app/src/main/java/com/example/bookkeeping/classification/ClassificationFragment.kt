package com.example.bookkeeping.classification

import android.content.Intent
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookkeeping.R
import com.example.bookkeeping.base.BaseFragment
import com.example.bookkeeping.classification_add.ClassificationAddActivity
import com.example.bookkeeping.db.AppDatabase
import com.example.bookkeeping.db.MenuDao
import com.example.bookkeeping.db.entity.Menu
import com.example.bookkeeping.utils.showDialogYN
import kotlinx.android.synthetic.main.classification_fragment_layout.*

/**
 * Created by 虫虫 on 2021/6/17
 */
class ClassificationFragment : BaseFragment() {

    private lateinit var menuDao: MenuDao

    private lateinit var startActivitylaunch: ActivityResultLauncher<Intent>

    private val mViewModel: ClassificationViewModel by viewModels()

    private val mData: MutableList<Menu> = mutableListOf()

    val ADD = 0
    val DEL = 1

    private val mAdapter: ClassificationFragmentAdapter by lazy {
        ClassificationFragmentAdapter(mData)
    }

    override fun initLayout(): Int = R.layout.classification_fragment_layout

    override fun thisIntent() {
        //点击事件的回调方法
        startActivitylaunch =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                //获取数据库的数据
                mViewModel.getItemList(menuDao)
            }
    }

    override fun initView() {
        //初始化menuDao
        menuDao = AppDatabase.getInstance(requireContext()).menuDao()
        //获取数据库的数据
        mViewModel.getItemList(menuDao)
        //设置适配器
        rv.layoutManager = LinearLayoutManager(context)
        rv.adapter = mAdapter
    }

    override fun initData() {
        //设置点击添加按钮的点击事件
        class_add.setOnClickListener {
            startActivitylaunch.launch(
                Intent().setClass(
                    requireContext(),
                    ClassificationAddActivity::class.java
                )
            )
        }
        initObserver()

        //设置列表中加减号的点击事件
        mAdapter.addChildClickViewIds(R.id.iv_add)
        mAdapter.addChildClickViewIds(R.id.iv_del)
        mAdapter.setOnItemChildClickListener { adapter, view, position ->
            val name = mData[position].menuName
            if (view.id == R.id.iv_add) {
                //加
                showDialogYN(
                    mData[position].menuName,
                    this.getString(R.string.add_num),
                    requireContext(),
                    ADD,
                    ::confirm
                )
            } else if (view.id == R.id.iv_del) {
                //减
                showDialogYN(
                    mData[position].menuName,
                    this.getString(R.string.del_num),
                    requireContext(),
                    DEL,
                    ::confirm
                )
            }
        }
    }

    /**
     * 用来监听MutableLiveData集合，当集合改变的时候，去刷新适配器
     */
    private fun initObserver() {
        mViewModel.itemListLiveData.observe(this, Observer {
            mData.clear()
            mData.addAll(it)
            mAdapter.notifyDataSetChanged()
        })
    }

    /**
     * 弹出框的确定按钮回调
     */
    private fun confirm(num: Long, flag: Int) {
        when (flag) {
            ADD -> Log.e("-----", "加数字$num")
            DEL -> Log.e("-----", "减数字$num")
        }
    }

    //单例模式
    companion object {
        fun newInstance(): ClassificationFragment {
            return ClassificationFragment()
        }
    }
}