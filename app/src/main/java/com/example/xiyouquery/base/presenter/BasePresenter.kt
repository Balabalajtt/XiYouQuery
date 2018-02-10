package com.example.xiyouquery.base.presenter

import com.example.xiyouquery.base.presenter.view.BaseView

/**
 * Created by 江婷婷 on 2018/2/10.
 */
open class BasePresenter<T: BaseView> {
    lateinit var mView: T
}