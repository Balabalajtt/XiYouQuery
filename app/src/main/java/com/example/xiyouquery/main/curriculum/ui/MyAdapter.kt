package com.example.xiyouquery.main.curriculum.ui

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import android.view.LayoutInflater
import com.example.xiyouquery.R


/**
 * Created by 江婷婷 on 2018/3/4.
 */
class MyAdapter(private val context: Context, val data: ArrayList<String>) : BaseAdapter() {
    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var view = p1
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.grib_item, null)
        }
        val textView = view!!.findViewById<TextView>(R.id.mText)
        //如果有课,那么添加数据
        if (getItem(p0) != "") {
            textView.text = getItem(p0) as String
        }
        return view
    }

    override fun getItem(p0: Int): Any {
        return data[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getCount(): Int {
        return data.size
    }

}