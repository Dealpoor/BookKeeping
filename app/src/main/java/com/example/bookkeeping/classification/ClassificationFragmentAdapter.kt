package com.example.bookkeeping.classification

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.bookkeeping.R
import com.example.bookkeeping.db.entity.Menu

/**
 * Created by 虫虫 on 2021/6/17
 */
class ClassificationFragmentAdapter(
    itemList: MutableList<Menu>
) : BaseQuickAdapter<Menu, BaseViewHolder>(R.layout.classification_adapter_layout, itemList) {

    override fun convert(holder: BaseViewHolder, item: Menu) {
        holder.setText(R.id.tv_name, item.menuName)
        holder.setText(R.id.tv_num, "" + item.menuNum)
    }

}