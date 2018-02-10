package com.example.xiyouquery.login.presenter

import android.content.Context
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
import com.example.xiyouquery.login.presenter.view.LoginView
import com.example.xiyouquery.login.service.impl.UserServiceImpl
import okhttp3.ResponseBody

/**
 * Created by 江婷婷 on 2018/2/10.
 */
class LoginPresenter : BasePresenter<LoginView>() {
    fun login(id: String, pwd: String, checkCode: String) {

        UserServiceImpl().login(id, pwd, checkCode)
                .execute(object : BaseSubscriber<ResponseBody>() {
                    override fun onNext(t: ResponseBody) {
                        mView.onLoginResult(true, 0)
                        Log.d("login", t.string())
                    }
                })
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