package com.example.xiyouquery.zf.service.impl

import com.example.xiyouquery.base.common.Constant
import com.example.xiyouquery.base.net.ZFRetrofitFactory
import com.example.xiyouquery.main.data.protocol.StudentInfo
import com.example.xiyouquery.zf.data.api.GradeApi
import com.example.xiyouquery.zf.data.protocol.Hidden
import com.example.xiyouquery.zf.service.GradeService
import okhttp3.ResponseBody
import rx.Observable
import java.net.URLEncoder

/**
 * Created by 江婷婷 on 2018/3/3.
 */
class GradeServiceImpl : GradeService {
    override fun getGrade(xn: String, xq: String): Observable<ResponseBody> {
        val map = mapOf(
                "__EVENTARGUMENT" to Hidden.__EVENTARGUMENT,
                "__EVENTTARGET" to Hidden.__EVENTTARGET,
                "__VIEWSTATE" to Hidden.__VIEWSTATE,
                "btn_xq" to "%D1%A7%C6%DA%B3%C9%BC%A8",
                "ddl_kcxz" to "",
                "ddlXN" to xn,
                "ddlXQ" to xq,
                "hidLanguage" to ""
        )

        val referer = "${Constant.GRADE_URL}xh=${StudentInfo.id}&xm=${URLEncoder.encode(StudentInfo.name, "gb2312")}&gnmkdm=N121605"
        return ZFRetrofitFactory.INSTANCE.create(GradeApi::class.java)
                .getGrade(referer, StudentInfo.id, StudentInfo.name, map = map)
    }


    override fun getGradeMenu(xh: String, xm: String): Observable<ResponseBody> {
        return ZFRetrofitFactory.INSTANCE.create(GradeApi::class.java)
                .getGradeMenu("http://222.24.62.120/xs_main.aspx?xh=${StudentInfo.id}", xh, xm)
    }

}