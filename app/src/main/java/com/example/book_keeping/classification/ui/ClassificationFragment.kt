package com.example.book_keeping.classification.ui

import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.book_keeping.R
import com.example.book_keeping.classification.model.ClassificationFragmentAdapter
import com.example.book_keeping.classification.model.ClassificationViewModel
import com.example.book_keeping.common.base.BaseFragment
import com.example.book_keeping.common.db.AppDatabase
import com.example.book_keeping.common.db.MenuDao
import com.example.book_keeping.common.db.RecordDao
import com.example.book_keeping.common.db.entity.Menu
import com.example.book_keeping.common.db.entity.Record
import com.example.book_keeping.utils.showDialogYN
import kotlinx.android.synthetic.main.classification_fragment_layout.*
import kotlinx.android.synthetic.main.common_title_layout.*
import java.text.ParsePosition
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by 虫虫 on 2021/6/17
 * 分类页面，展示当前添加的各种商品
 */
class ClassificationFragment : BaseFragment() {

    private val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

    private lateinit var menuDao: MenuDao

    private lateinit var recordDao: RecordDao

    private val mData: MutableList<Menu> = mutableListOf()

    private lateinit var startActivitylaunch: ActivityResultLauncher<Intent>

    private val mViewModel: ClassificationViewModel by viewModels()

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
        //设置标题文字
        title_name.text = getString(R.string.classification_fragment_title)
        title_right.text = getString(R.string.classification_fragment_title_add)
        //初始化menuDao
        menuDao = AppDatabase.getInstance(requireContext()).menuDao()
        //初始化recordDao
        recordDao = AppDatabase.getInstance(requireContext()).recordDao()
        //获取数据库的数据
        mViewModel.getItemList(menuDao)
        //设置适配器
        rv.layoutManager = LinearLayoutManager(context)
        rv.adapter = mAdapter
    }

    override fun initData() {
        //设置点击添加按钮的点击事件
        title_right.setOnClickListener {
            startActivitylaunch.launch(
                Intent().setClass(
                    requireContext(),
                    ClassificationAddActivity::class.java
                )
            )
        }
        initObserver()

        //设置列表中加减号的点击事件
        mAdapter.run {
            addChildClickViewIds(R.id.iv_add)
            addChildClickViewIds(R.id.iv_del)
            setOnItemChildClickListener { _, view, position ->
                if (view.id == R.id.iv_add) {
                    //加
                    showDialogYN(
                        position,
                        mData[position].menuName,
                        mData[position].menuId,
                        mData[position].menuNum,
                        getString(R.string.classification_dialog_hint_add_num),
                        requireContext(),
                        FLAG_ADD,
                        ::confirm
                    )
                } else if (view.id == R.id.iv_del) {
                    //减
                    showDialogYN(
                        position,
                        mData[position].menuName,
                        mData[position].menuId,
                        mData[position].menuNum,
                        getString(R.string.classification_dialog_hint_del_num),
                        requireContext(),
                        FLAG_DEL,
                        ::confirm
                    )
                }
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
    private fun confirm(
        position: Int,
        num: Long,
        flag: Int,
        menuName: String,
        menuId: Long,
        menuNum: Long
    ) {
        //获取系统当前时间
        val formatted = simpleDateFormat.format(Date())

        //将当前时间转成date存储起来
        val date = simpleDateFormat.parse(formatted, ParsePosition(0)).time

        when (flag) {
            //加
            FLAG_ADD -> {
                val count = menuNum + num
                val record = "$formatted : ${menuName}增加了${num}"

                //修改菜单数量
                menuDao.updateMenuNum(menuId, count)
                //新增一条记录
                recordDao.addRecord(Record(0, menuId, record, date))

                //刷新页面
                mData[position].menuNum = count
                mAdapter.notifyDataSetChanged()
            }

            //减
            FLAG_DEL -> {
                val count = menuNum - num
                val record = "$formatted : ${menuName}减少了${num}"

                //修改菜单数量
                menuDao.updateMenuNum(menuId, count)
                //新增一条记录
                recordDao.addRecord(Record(0, menuId, record, date))

                //刷新页面
                mData[position].menuNum = count
                mAdapter.notifyDataSetChanged()
            }
        }
    }


    companion object {
        const val FLAG_ADD = 0
        const val FLAG_DEL = 1

        fun newInstance(): ClassificationFragment {
            return ClassificationFragment()
        }
    }
}