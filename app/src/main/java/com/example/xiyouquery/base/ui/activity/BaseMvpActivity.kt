package com.example.xiyouquery.base.ui.activity

import com.example.xiyouquery.base.presenter.BasePresenter
import com.example.xiyouquery.base.presenter.view.BaseView

/**
 * Created by 江婷婷 on 2018/2/10.
 */
open class BaseMvpActivity<T: BasePresenter<*>> : BaseActivity(), BaseView {
    lateinit var mPresenter: T

    override fun start() {}

}