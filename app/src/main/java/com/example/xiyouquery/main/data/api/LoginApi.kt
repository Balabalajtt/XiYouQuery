package com.example.xiyouquery.main.data.api

import com.example.xiyouquery.base.common.Constant.LOGIN_POST_URL
import okhttp3.ResponseBody
import retrofit2.http.*
import rx.Observable

/**
 * Created by 江婷婷 on 2018/2/10.
 */
interface LoginApi {
    @FormUrlEncoded
    @POST(LOGIN_POST_URL)
    @Headers("Content-Type: application/x-www-form-urlencoded",
            "Referer: http://222.24.62.120/"
            )
    fun login(@FieldMap map: Map<String, String>): Observable<ResponseBody>

}