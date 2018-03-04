package com.example.xiyouquery.main.grade.data.api

import com.example.xiyouquery.base.common.Constant
import okhttp3.ResponseBody
import retrofit2.http.*
import rx.Observable

/**
 * Created by 江婷婷 on 2018/3/3.
 */
interface GradeApi {
    @GET(Constant.GRADE_URL)
    fun getGradeMenu(@Header("Referer")referer: String, @Query("xh") xh: String, @Query("xm") xm: String, @Query("gnmkdm") gnmkdm: String = "N121605" ) : Observable<ResponseBody>


    @POST(Constant.GRADE_URL)
    @FormUrlEncoded
    @Headers("Content-Type: application/x-www-form-urlencoded")
//            "Referer: http://222.24.62.120/xscjcx.aspx?xh=04163216&xm=%BD%AD%E6%C3%E6%C3&gnmkdm=N121605")
    fun getGrade(@Header("Referer") referer: String, @Query("xh") xh: String, @Query("xm") xm: String, @Query("gnmkdm") gnmkdm: String = "N121605", @FieldMap map: Map<String, String>) : Observable<ResponseBody>

}