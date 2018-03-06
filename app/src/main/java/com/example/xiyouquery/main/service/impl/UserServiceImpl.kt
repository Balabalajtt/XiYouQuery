package com.example.xiyouquery.main.service.impl

import com.example.xiyouquery.base.net.ZFRetrofitFactory
import com.example.xiyouquery.main.data.api.LoginApi
import com.example.xiyouquery.main.data.protocol.StudentInfo
import com.example.xiyouquery.main.service.UserService
import okhttp3.ResponseBody
import rx.Observable

/**
 * Created by 江婷婷 on 2018/2/10.
 */
class UserServiceImpl : UserService {
    override fun login(id: String, pwd: String, checkCode: String) : Observable<ResponseBody> {
        val map = mapOf("__VIEWSTATE" to "dDwxNTMxMDk5Mzc0Ozs+lYSKnsl/mKGQ7CKkWFJpv0btUa8=",
                "txtUserName" to id,
                "Textbox1" to "",
                "TextBox2" to pwd,
                "txtSecretCode" to checkCode,
                "RadioButtonList1" to "%D1%A7%C9%FA",
                "Button1" to "",
                "lbLanguage" to "",
                "hidPdrs" to "",
                "hidsc" to "")

        StudentInfo.id = id

        return ZFRetrofitFactory.INSTANCE.create(LoginApi::class.java)
                .login(map)
    }

}