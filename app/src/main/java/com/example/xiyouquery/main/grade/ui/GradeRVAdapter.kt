package com.example.xiyouquery.main.grade.ui

import android.content.Context
import android.support.v7.view.menu.MenuView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.xiyouquery.R
import com.example.xiyouquery.main.grade.data.protocol.GradeList

/**
 * Created by 江婷婷 on 2018/3/6.
 */
class GradeRVAdapter(val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val layoutInflater = LayoutInflater.from(context)

    override fun getItemCount(): Int {
        return GradeList.allGrade.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        (holder as GradeHolder).mNameTv.text = GradeList.allGrade[position].name
        holder.mPropertyTv.text = GradeList.allGrade[position].property
        holder.mCreditTv.text = "学分" + GradeList.allGrade[position].credit
        holder.mPointTv.text = "绩点：" + GradeList.allGrade[position].point
        holder.mGradeTv.text = "成绩：" + GradeList.allGrade[position].grade
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        return GradeHolder(layoutInflater.inflate(R.layout.fragment_grade_recycler_item, parent, false))
    }

    class GradeHolder(view: View) : RecyclerView.ViewHolder(view) {
        var mNameTv: TextView = view.findViewById(R.id.mNameTv)
        var mPropertyTv: TextView = view.findViewById(R.id.mPropertyTv)
        var mCreditTv: TextView = view.findViewById(R.id.mCreditTv)
        var mPointTv: TextView = view.findViewById(R.id.mPointTv)
        var mGradeTv: TextView = view.findViewById(R.id.mGradeTv)
    }
}