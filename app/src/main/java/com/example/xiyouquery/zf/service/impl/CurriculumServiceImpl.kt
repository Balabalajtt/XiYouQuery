package com.example.xiyouquery.zf.service.impl

import com.example.xiyouquery.base.net.ZFRetrofitFactory
import com.example.xiyouquery.zf.data.api.CurriculumApi
import com.example.xiyouquery.zf.service.CurriculumService
import okhttp3.ResponseBody
import rx.Observable

/**
 * Created by 江婷婷 on 2018/3/4.
 */
class CurriculumServiceImpl : CurriculumService {
    override fun getInitCurriculum(xh: String, xm: String): Observable<ResponseBody> {
        val referer = "http://222.24.62.120/xs_main.aspx?xh=$xh"
        return ZFRetrofitFactory.INSTANCE.create(CurriculumApi::class.java)
                .getInitCurriculum(referer, xh, xm)
    }
}