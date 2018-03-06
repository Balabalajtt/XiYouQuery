package com.example.xiyouquery.zf.data.api

import com.example.xiyouquery.base.common.Constant
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import rx.Observable

/**
 * Created by 江婷婷 on 2018/3/4.
 */
interface CurriculumApi {
    @GET(Constant.CURRICULUM_URL)
    fun getInitCurriculum(@Header("Referer")referer: String, @Query("xh") xh: String, @Query("xm") xm: String, @Query("gnmkdm") gnmkdm: String = "N121603" ) : Observable<ResponseBody>

}