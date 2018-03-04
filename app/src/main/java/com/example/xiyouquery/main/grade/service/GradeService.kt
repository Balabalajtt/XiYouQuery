package com.example.xiyouquery.main.grade.service

import android.content.Context
import okhttp3.ResponseBody
import rx.Observable

/**
 * Created by 江婷婷 on 2018/3/3.
 */
interface GradeService {
    fun getGradeMenu(xh: String, xm: String) : Observable<ResponseBody>
    fun getGrade(xn: String, xq: String) : Observable<ResponseBody>
}