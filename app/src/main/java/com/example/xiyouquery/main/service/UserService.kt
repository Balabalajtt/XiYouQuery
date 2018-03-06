package com.example.xiyouquery.main.service

import okhttp3.ResponseBody
import rx.Observable

/**
 * Created by 江婷婷 on 2018/2/10.
 */
interface UserService {
    fun login(id: String, pwd: String, checkCode: String) : Observable<ResponseBody>
}