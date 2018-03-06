package com.example.xiyouquery.login.presenter

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.example.xiyouquery.base.common.Constant
import com.example.xiyouquery.base.ext.execute
import com.example.xiyouquery.base.presenter.BasePresenter
import com.example.xiyouquery.base.rx.BaseSubscriber
import com.example.xiyouquery.login.data.protocol.LoginStatus
import com.example.xiyouquery.login.data.protocol.StudentInfo
import com.example.xiyouquery.login.presenter.view.LoginView
import com.example.xiyouquery.login.service.impl.UserServiceImpl
import okhttp3.ResponseBody
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

/**
 * Created by 江婷婷 on 2018/2/10.
 */
class LoginPresenter : BasePresenter<LoginView>() {

    fun getLocalAccount(context: Context) : ArrayList<String> {
        val pref = context.getSharedPreferences("account", 0)
        return if (pref.getBoolean("isRemember", false)) {
            arrayListOf(pref.getString("id", ""), pref.getString("pwd", ""))
        } else {
            arrayListOf()
        }

    }

    fun saveAccount(id: String, pwd: String, context: Context) {
        val editor = context.getSharedPreferences("account", 0).edit()
        editor.putBoolean("isRemember", true)
        editor.putString("id", id)
        editor.putString("pwd", pwd)
        editor.apply()
    }

    fun deleteAccount(context: Context) {
        val editor = context.getSharedPreferences("account", 0).edit()
        editor.putBoolean("isRemember", false)
        editor.apply()
    }

    fun login(id: String, pwd: String, checkCode: String) {
        UserServiceImpl().login(id, pwd, checkCode)
                .execute(object : BaseSubscriber<ResponseBody>() {
                    override fun onNext(t: ResponseBody) {
                        parseLoginResponse(t.string())
                        mView.onLoginResult(LoginStatus.status, LoginStatus.message)

                    }
                })
    }

    fun parseLoginResponse(response: String) {
        val doc: Document = Jsoup.parse(response)

        val title = doc.getElementsByTag("title").text()
        if (title == "正方教务管理系统") {
            StudentInfo.name = doc.getElementById("xhxm").text()
            LoginStatus.status = true
            LoginStatus.message = "${StudentInfo.name}，您好"
//            Log.d("aaaaaaaaaaaa", "${StudentInfo.name}，您好")

        } else {
            val script = doc.getElementsByTag("script")[1].data().toString()
            val message = script.substring(script.indexOf("('") + 2, script.indexOf("')"))
            StudentInfo.name = ""
            LoginStatus.status = false
            LoginStatus.message = message
        }

    }

    fun getCheckCode(context: Context, view: ImageView) {
        Glide.with(context)
                .load(GlideUrl(Constant.SERVER_ADDRESS + Constant.CHECK_CODE_URL, LazyHeaders.Builder()
                        .addHeader("Cookie", "ASP.NET_SessionId=z03gtbnnl2pzaf55bj252f45").build()))
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(view)

    }

}