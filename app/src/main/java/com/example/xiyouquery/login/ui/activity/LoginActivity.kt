package com.example.xiyouquery.login.ui.activity

import android.os.Bundle
import com.example.xiyouquery.R
import com.example.xiyouquery.base.ui.activity.BaseMvpActivity
import com.example.xiyouquery.login.presenter.LoginPresenter
import com.example.xiyouquery.login.presenter.view.LoginView
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.toast

class LoginActivity : BaseMvpActivity<LoginPresenter>(), LoginView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val context = this
        mPresenter = LoginPresenter()
        mPresenter.mView = this

        mPresenter.getCheckCode(this, mCheckCodeImg)

        mLoginBtn.onClick {
            mPresenter.login(mUserNameEd.text.toString(), mPwdEd.text.toString(), mCheckCodeEd.text.toString())
        }

        mCheckCodeImg.onClick {
            mPresenter.getCheckCode(context, mCheckCodeImg)
        }

    }

    override fun onLoginResult(result: Boolean, type: Int) {
        toast("请求成功" + result + type)
    }


}