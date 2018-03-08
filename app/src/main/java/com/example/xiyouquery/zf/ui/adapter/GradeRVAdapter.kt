package com.example.xiyouquery.zf.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.xiyouquery.R
import com.example.xiyouquery.zf.data.protocol.Grade

/**
 * Created by 江婷婷 on 2018/3/6.
 */
class GradeRVAdapter(context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val layoutInflater = LayoutInflater.from(context)
    private var data: ArrayList<Grade> = arrayListOf()

    override fun getItemCount(): Int {
        return data.size
    }

    fun setData(data: ArrayList<Grade>) {
        this.data = data
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        (holder as GradeHolder).mNameTv.text = data[position].name
        holder.mPropertyTv.text = data[position].property
        holder.mCreditTv.text = "学分" + data[position].credit
        holder.mPointTv.text = "绩点：" + data[position].point
        holder.mGradeTv.text = "成绩：" + data[position].grade
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