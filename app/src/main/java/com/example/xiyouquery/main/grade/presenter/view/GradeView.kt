package com.example.xiyouquery.main.grade.presenter.view

import android.content.Context
import com.example.xiyouquery.base.presenter.view.BaseView
import com.example.xiyouquery.main.grade.data.protocol.Grade

/**
 * Created by 江婷婷 on 2018/3/3.
 */
interface GradeView : BaseView {
    fun onGetGradeMenuResult()
    fun onGetGradeResult()
}