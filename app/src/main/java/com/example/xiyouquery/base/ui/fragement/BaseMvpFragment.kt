package com.example.xiyouquery.base.ui.fragement

import android.support.v4.app.Fragment
import com.example.xiyouquery.base.presenter.BasePresenter
import com.example.xiyouquery.base.presenter.view.BaseView

/**
 * Created by 江婷婷 on 2018/3/3.
 */
open class BaseMvpFragment<T: BasePresenter<*>> : Fragment(), BaseView {
    lateinit var mPresenter: T

    override fun start() {}

}