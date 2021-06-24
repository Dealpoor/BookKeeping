package com.example.bookkeeping.record

import com.example.bookkeeping.R
import com.example.bookkeeping.base.BaseFragment
import com.example.bookkeeping.classification.ClassificationFragment

/**
 * Created by 虫虫 on 2021/6/17
 */
class RecordFragment : BaseFragment() {

    override fun initLayout() = R.layout.record_fragment_layout

    override fun thisIntent() {

    }

    override fun initView() {

    }

    override fun initData() {

    }

    companion object {
        fun newInstance(): RecordFragment {
            return RecordFragment()
        }
    }
}