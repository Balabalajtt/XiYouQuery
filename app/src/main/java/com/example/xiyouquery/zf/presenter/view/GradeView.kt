package com.example.xiyouquery.zf.presenter.view

import com.example.xiyouquery.base.presenter.view.BaseView

/**
 * Created by 江婷婷 on 2018/3/3.
 */
interface GradeView : BaseView {
    fun onGetGradeMenuResult(yearList: ArrayList<String>, termList: ArrayList<String>, semesterList: ArrayList<String>)
    fun onGradeError(message: String)
    fun onNotifyGrade()

}