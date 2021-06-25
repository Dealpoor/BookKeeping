package com.example.book_keeping.main.ui

import androidx.fragment.app.Fragment
import com.example.book_keeping.R
import com.example.book_keeping.classification.ui.ClassificationFragment
import com.example.book_keeping.common.base.BaseActivity
import com.example.book_keeping.record.ui.RecordFragment
import kotlinx.android.synthetic.main.main_activity_layout.*

/**
 * 主Activity，用来展示两个fragment
 */
class MainActivity : BaseActivity() {

    private val mFragments = mutableListOf<Fragment>()

    private var lastIndex = 0

    override fun initLayout(): Int {
        return R.layout.main_activity_layout
    }

    override fun initView() {
        initBottomNavigation()
    }

    override fun initData() {
        mFragments.add(ClassificationFragment.newInstance())
        mFragments.add(RecordFragment.newInstance())
        // 初始化展示MessageFragment
        setFragmentPosition(0)
    }

    private fun initBottomNavigation() {
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_message -> setFragmentPosition(0)
                R.id.menu_contacts -> setFragmentPosition(1)
                else -> {
                }
            }
            true
        }
    }

    private fun setFragmentPosition(position: Int) {
        val ft = supportFragmentManager.beginTransaction()
        val currentFragment = mFragments[position]
        val lastFragment = mFragments[lastIndex]
        lastIndex = position
        ft.hide(lastFragment)
        if (!currentFragment.isAdded) {
            supportFragmentManager.beginTransaction().remove(currentFragment).commit()
            ft.add(R.id.ll_frameLayout, currentFragment)
        }
        ft.show(currentFragment)
        ft.commitAllowingStateLoss()
    }
}