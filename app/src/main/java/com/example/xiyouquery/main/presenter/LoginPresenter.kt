package com.example.xiyouquery.main.presenter

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.example.xiyouquery.base.common.Constant
import com.example.xiyouquery.base.ext.execute
import com.example.xiyouquery.base.presenter.BasePresenter
import com.example.xiyouquery.base.rx.BaseSubscriber
import com.example.xiyouquery.main.data.protocol.LoginStatus
import com.example.xiyouquery.main.data.protocol.StudentInfo
import com.example.xiyouquery.main.presenter.view.LoginView
import com.example.xiyouquery.main.service.impl.UserServiceImpl
import okhttp3.ResponseBody
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import rx.Observable
import rx.functions.Func1

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

    fun getCheckCode(context: Context, view: ImageView) {
        Glide.with(context)
                .load(GlideUrl(Constant.SERVER_ADDRESS + Constant.CHECK_CODE_URL, LazyHeaders.Builder()
                        .addHeader("Cookie", "ASP.NET_SessionId=z03gtbnnl2pzaf55bj252f45").build()))
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(view)

    }

    fun login(id: String, pwd: String, checkCode: String) {
        UserServiceImpl().login(id, pwd, checkCode)
                .flatMap(Func1<ResponseBody, Observable<LoginStatus>> { t ->
                    val doc: Document = Jsoup.parse(t.string())
                    val title = doc.getElementsByTag("title").text()
                    if (title == "正方教务管理系统") {
                        StudentInfo.name = doc.getElementById("xhxm").text()
                        return@Func1 Observable.just(LoginStatus(true, "${StudentInfo.name}你好"))
                    } else {
                        val script = doc.getElementsByTag("script")[1].data().toString()
                        val message = script.substring(script.indexOf("('") + 2, script.indexOf("')"))
                        StudentInfo.name = ""
                        return@Func1 Observable.just(LoginStatus(false, message))
                    }
                })
                .execute(object : BaseSubscriber<LoginStatus>() {
                    override fun onNext(t: LoginStatus) {
                        mView.onLoginResult(t.status, t.msg)

                    }
                })
    }

}