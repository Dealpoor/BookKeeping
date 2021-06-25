package com.example.book_keeping.record.ui

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
import kotlinx.android.synthetic.main.common_title_layout.*

/**
 * Created by 虫虫 on 2021/6/17
 */
class RecordFragment : BaseFragment() {

    private lateinit var recordDao: RecordDao

    private val mViewModel: RecordViewModel by viewModels()

    private val mData: MutableList<Record> = mutableListOf()

    override fun initLayout() = R.layout.record_fragment_layout

    override fun thisIntent() {

    }

    override fun initView() {
        //设置标题文字
        title_name.text = getString(R.string.record_fragment_title)
        //初始化recordDao
        recordDao = AppDatabase.getInstance(requireContext()).recordDao()

    }

    override fun initData() {
        mViewModel.getItemList(recordDao)

        //设置适配器
        rv.layoutManager = LinearLayoutManager(context)
        rv.adapter = mAdapter

        initObserver()
    }

    companion object {
        fun newInstance(): RecordFragment {
            return RecordFragment()
        }
    }

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
}