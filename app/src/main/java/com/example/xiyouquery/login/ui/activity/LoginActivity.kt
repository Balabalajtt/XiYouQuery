package com.example.xiyouquery.login.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.SpannableStringBuilder
import com.example.xiyouquery.R
import com.example.xiyouquery.base.ui.activity.BaseMvpActivity
import com.example.xiyouquery.login.presenter.LoginPresenter
import com.example.xiyouquery.login.presenter.view.LoginView
import com.example.xiyouquery.main.ui.activity.MainActivity
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.toast

class LoginActivity : BaseMvpActivity<LoginPresenter>(), LoginView {

    private lateinit var loginContext: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginContext = this
        mPresenter = LoginPresenter()
        mPresenter.mView = this

        mPresenter.getCheckCode(this, mCheckCodeImg)

        val list = mPresenter.getLocalAccount(loginContext)
        if (list.isNotEmpty()) {
            mUserNameEd.text = SpannableStringBuilder(list[0])
            mPwdEd.text = SpannableStringBuilder(list[1])
            mSaveBtn.isChecked = true
        }

        mLoginBtn.onClick {
            if(mSaveBtn.isChecked) {
                mPresenter.saveAccount(mUserNameEd.text.toString(), mPwdEd.text.toString(), loginContext)
            } else {
                mPresenter.deleteAccount(loginContext)
            }
            mPresenter.login(mUserNameEd.text.toString(), mPwdEd.text.toString(), mCheckCodeEd.text.toString())
        }

        mCheckCodeImg.onClick {
            mPresenter.getCheckCode(loginContext, mCheckCodeImg)
        }

    }

    override fun onLoginResult(result: Boolean, message: String) {
        toast(message)
        if (result) {
            startActivity(Intent(this, MainActivity::class.java))
        } else {
            mPresenter.getCheckCode(loginContext, mCheckCodeImg)
        }
    }
}