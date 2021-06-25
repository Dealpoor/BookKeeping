package com.example.book_keeping.record.model

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.book_keeping.R
import com.example.book_keeping.common.db.entity.Menu
import com.example.book_keeping.common.db.entity.Record

/**
 * Created by 虫虫 on 2021/6/17
 */
class RecordFragmentAdapter(
    itemList: MutableList<Record>
) : BaseQuickAdapter<Record, BaseViewHolder>(R.layout.record_adapter_item_layout, itemList) {

    override fun convert(holder: BaseViewHolder, item: Record) {
        holder.setText(R.id.tv_name, item.recordItem)
    }

}