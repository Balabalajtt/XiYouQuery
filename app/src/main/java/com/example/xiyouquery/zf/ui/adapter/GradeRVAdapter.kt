package com.example.xiyouquery.zf.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.xiyouquery.R
import com.example.xiyouquery.zf.data.protocol.GradeData

/**
 * Created by 江婷婷 on 2018/3/6.
 */
class GradeRVAdapter(context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val layoutInflater = LayoutInflater.from(context)

    override fun getItemCount(): Int {
        return GradeData.allGrade.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        (holder as GradeHolder).mNameTv.text = GradeData.allGrade[position].name
        holder.mPropertyTv.text = GradeData.allGrade[position].property
        holder.mCreditTv.text = "学分" + GradeData.allGrade[position].credit
        holder.mPointTv.text = "绩点：" + GradeData.allGrade[position].point
        holder.mGradeTv.text = "成绩：" + GradeData.allGrade[position].grade
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