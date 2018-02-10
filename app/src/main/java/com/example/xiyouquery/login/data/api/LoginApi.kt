package com.example.xiyouquery.login.data.api

import com.example.xiyouquery.base.common.Constant.LOGIN_POST_URL
import okhttp3.ResponseBody
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import rx.Observable

/**
 * Created by 江婷婷 on 2018/2/10.
 */
interface LoginApi {
    @FormUrlEncoded
    @POST(LOGIN_POST_URL)
    fun login(@FieldMap map: Map<String, String>) : Observable<ResponseBody>

}