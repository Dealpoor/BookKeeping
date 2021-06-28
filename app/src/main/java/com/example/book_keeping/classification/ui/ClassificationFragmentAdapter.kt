package com.example.book_keeping.classification.ui

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.book_keeping.R
import com.example.book_keeping.classification.model.Menu

/**
 * Created by 虫虫 on 2021/6/17
 * 商品页面列表适配器
 */
class ClassificationFragmentAdapter(
    itemList: MutableList<Menu>
) : BaseQuickAdapter<Menu, BaseViewHolder>(R.layout.classification_adapter_layout, itemList) {

    override fun convert(holder: BaseViewHolder, item: Menu) {
        holder.setText(R.id.tv_name, item.menuName)
        holder.setText(R.id.tv_num, "" + item.menuNum)
    }

}