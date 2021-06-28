package com.example.book_keeping.record.ui

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.book_keeping.R
import com.example.book_keeping.record.model.Record

/**
 * Created by 虫虫 on 2021/6/17
 * 统计页面的列表适配器
 */
class RecordFragmentAdapter(
    itemList: MutableList<Record>
) : BaseQuickAdapter<Record, BaseViewHolder>(R.layout.record_adapter_item_layout, itemList) {

    override fun convert(holder: BaseViewHolder, item: Record) {
        holder.setText(R.id.tv_name, item.recordItem)
    }

}