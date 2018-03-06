package com.example.xiyouquery.zf.ui.adapter

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.xiyouquery.zf.ui.fragment.CurriculumFragment
import com.example.xiyouquery.zf.ui.fragment.GradeFragment

/**
 * Created by 江婷婷 on 2018/2/25.
 */
class ViewPagerAdapter(manager: FragmentManager, private val titles: ArrayList<String>, private val context: Context) : FragmentPagerAdapter(manager) {
    override fun getItem(position: Int): Fragment {
        return if (position == 0) {
            GradeFragment().getGradeFragment(context)
        } else {
            CurriculumFragment().getCurriculumFragment(context)
        }
    }

    override fun getCount(): Int {
        return titles.size
    }

}