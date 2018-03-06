package com.example.xiyouquery.zf.service

import okhttp3.ResponseBody
import rx.Observable

/**
 * Created by 江婷婷 on 2018/3/4.
 */
interface CurriculumService {
    fun getInitCurriculum(xh: String, xm: String) : Observable<ResponseBody>
}