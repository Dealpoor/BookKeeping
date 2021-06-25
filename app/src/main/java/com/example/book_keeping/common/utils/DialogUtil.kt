package com.example.book_keeping.utils

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.book_keeping.R
import kotlinx.android.synthetic.main.common_dialog_add_del_layout.view.*

/**
 * Created by 虫虫 on 2021/6/23
 */

private var mAlertDialog: AlertDialog? = null

fun showDialogYN(
    position: Int,
    menuName: String,
    menuId: Long,
    menuNum: Long,
    hint: String,
    context: Context,
    flag: Int,
    confirm: (Int, Long, Int, String, Long, Long) -> Unit
) {
    if (mAlertDialog != null && context == mAlertDialog?.context && mAlertDialog!!.isShowing) {
        //表示在同一个activity，已经显示了，就不再显示
        return
    }
    if (mAlertDialog == null || context != mAlertDialog?.context) {
        mAlertDialog = AlertDialog.Builder(context, R.style.MyDialogStyle).create()//背景透明的dialog
    }
    val view1 = View.inflate(context, R.layout.common_dialog_add_del_layout, null)//有ready

    mAlertDialog?.setView(view1)
    mAlertDialog?.show()

    view1.tv_menuName.text = menuName
    view1.et_menuNum.hint = hint

    view1.tv_confirm.setOnClickListener {
        //按了yes按钮
        if (view1.et_menuNum.text.toString().trim() == "") {
            Toast.makeText(context, R.string.classification_dialog_toast_num, Toast.LENGTH_SHORT)
                .show()
            return@setOnClickListener
        }
        confirm(
            position,
            view1.et_menuNum.text.toString().trim().toLong(),
            flag,
            menuName,
            menuId,
            menuNum
        )
        mAlertDialog?.dismiss()
    }
    view1.tv_cancel.setOnClickListener {
        //按了no按钮
        mAlertDialog?.dismiss()
    }

}