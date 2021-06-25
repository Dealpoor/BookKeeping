package com.example.book_keeping.record.ui

import com.example.book_keeping.R
import com.example.book_keeping.common.base.BaseFragment
import kotlinx.android.synthetic.main.common_title_layout.*

/**
 * Created by 虫虫 on 2021/6/17
 */
class RecordFragment : BaseFragment() {

    override fun initLayout() = R.layout.record_fragment_layout

    override fun thisIntent() {

    }

    override fun initView() {
        //设置标题文字
        title_name.text = getString(R.string.record_fragment_title)

    }

    override fun initData() {

    }

    companion object {
        fun newInstance(): RecordFragment {
            return RecordFragment()
        }
    }
}