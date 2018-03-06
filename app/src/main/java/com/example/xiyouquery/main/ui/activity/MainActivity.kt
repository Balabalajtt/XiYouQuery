package com.example.xiyouquery.main.ui.activity

import android.os.Bundle
import com.example.xiyouquery.R
import com.example.xiyouquery.base.ui.activity.BaseActivity
import kotlinx.android.synthetic.main.content_layout.*
import com.example.xiyouquery.main.ui.adapter.ViewPagerAdapter

/**
 * Created by 江婷婷 on 2018/2/25.
 */
class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val titles = arrayListOf("成绩", "课表")
        mViewPager.adapter = ViewPagerAdapter(supportFragmentManager, titles, this)
        mTabLayout.setupWithViewPager(mViewPager)

        mTabLayout.addTab(mTabLayout.newTab())
        mTabLayout.addTab(mTabLayout.newTab())
        mTabLayout.getTabAt(0)!!.text = "成绩"
        mTabLayout.getTabAt(1)!!.text = "课表"

    }
}