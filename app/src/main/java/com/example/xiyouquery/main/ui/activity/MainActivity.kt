package com.example.xiyouquery.main.ui.activity

import android.os.Bundle
import com.example.xiyouquery.R
import com.example.xiyouquery.base.ui.activity.BaseActivity
import kotlinx.android.synthetic.main.content_layout.*
import android.support.v7.app.ActionBarDrawerToggle
import com.example.xiyouquery.main.ui.adapter.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by 江婷婷 on 2018/2/25.
 */
class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        setSupportActionBar(mToolbar)

//        val toggle = ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.open, R.string.close)
//        mDrawerLayout.addDrawerListener(toggle)
//        toggle.syncState()

        val titles = arrayListOf("成绩", "课表")
        mViewPager.adapter = ViewPagerAdapter(supportFragmentManager, titles, this)

        mTabLayout.setupWithViewPager(mViewPager)

//        val navView = findViewById(R.id.navigation_view) as NavigationView
//        val navHeader = navView.getHeaderView(0) as LinearLayout
//        val avatar = navHeader.findViewById(R.id.avatar) as ImageView
//        setCircleImageview(avatar)

    }
}