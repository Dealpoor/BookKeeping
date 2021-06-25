package com.example.book_keeping.record.ui

import android.app.DatePickerDialog
import android.util.Log
import android.widget.DatePicker
import android.widget.Toast
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
import com.example.book_keeping.record.model.RecordFragmentAdapter
import com.example.book_keeping.record.model.RecordViewModel
import kotlinx.android.synthetic.main.classification_fragment_layout.*
import kotlinx.android.synthetic.main.classification_fragment_layout.rv
import kotlinx.android.synthetic.main.common_title_layout.*
import kotlinx.android.synthetic.main.record_fragment_layout.*
import java.text.ParsePosition
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by 虫虫 on 2021/6/17
 */
class RecordFragment : BaseFragment(), DatePickerDialog.OnDateSetListener {

    private lateinit var recordDao: RecordDao

    //初始化ViewModel
    private val mViewModel: RecordViewModel by viewModels()

    //创建空集合用来存放MutableLiveData中的数据
    private val mData: MutableList<Record> = mutableListOf()

    //用来格式化时间
    private val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

    //初始化布局
    override fun initLayout() = R.layout.record_fragment_layout

    override fun thisIntent() {

    }

    override fun initView() {
        //设置标题文字
        title_name.text = getString(R.string.record_fragment_title)
        titlte_right.text = getString(R.string.record_fragment_title_right)
        //初始化recordDao
        recordDao = AppDatabase.getInstance(requireContext()).recordDao()
        //设置下拉刷新样式
        initRefresh()
        //设置日历的点击事件
        initDateClick()
    }

    private fun initDateClick() {
        titlte_right.setOnClickListener {
            val calendar = Calendar.getInstance()
            val dialog = DatePickerDialog(
                requireContext(), this, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )
            dialog.show()
        }
    }

    private fun initRefresh() {
        //设置下拉框的事件
        swipe_layout.run {
            setColorSchemeResources(R.color.classification_select)
            setOnRefreshListener {
                //下拉的时候查询数据库全部数据
                mViewModel.getItemList(recordDao)
                Toast.makeText(context, R.string.record_fragment_refresh_toast, Toast.LENGTH_SHORT)
                    .show()
                //关闭下拉刷新
                isRefreshing = false
            }
        }
    }

    override fun initData() {
        //查询所有数据
        mViewModel.getItemList(recordDao)

        //设置适配器
        rv.layoutManager = LinearLayoutManager(context)
        rv.adapter = mAdapter

        //绑定数据，刷新适配器
        initObserver()
    }

    companion object {
        fun newInstance(): RecordFragment {
            return RecordFragment()
        }
    }

    //初始化适配器
    private val mAdapter: RecordFragmentAdapter by lazy {
        RecordFragmentAdapter(mData)
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

    //获取选择的日期，根据日期去数据库查询当前时间段下面的所有数据
    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        //当前月份加一，因为返回的月份少一个月，他的格式是单数，所以把他前面补0，天数也是一样
        var thisMonth = "${month + 1}"
        var thisDayOfMonth = "$dayOfMonth"
        if (month < 10) {
            thisMonth = "0${month + 1}"
        }
        if (dayOfMonth < 10) {
            thisDayOfMonth = "0$dayOfMonth"
        }
        //将开始时间和结束时间转成时间戳
        val startTime =
            simpleDateFormat.parse(
                "$year-$thisMonth-$thisDayOfMonth 00:00:00",
                ParsePosition(0)
            ).time
        val endTime =
            simpleDateFormat.parse(
                "$year-$thisMonth-$thisDayOfMonth 23:59:59",
                ParsePosition(0)
            ).time

        mViewModel.getItemByTime(startTime, endTime, recordDao)
    }
}