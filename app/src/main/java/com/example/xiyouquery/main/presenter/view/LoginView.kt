package com.example.xiyouquery.main.presenter.view

import com.example.xiyouquery.base.presenter.view.BaseView

/**
 * Created by 江婷婷 on 2018/2/10.
 */
interface LoginView : BaseView {
    fun onLoginResult(result: Boolean, message: String)
}