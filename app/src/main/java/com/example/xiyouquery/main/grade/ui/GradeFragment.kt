package com.example.xiyouquery.main.grade.ui

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.xiyouquery.R
import com.example.xiyouquery.base.ui.fragement.BaseMvpFragment
import com.example.xiyouquery.main.grade.presenter.GradePresenter
import com.example.xiyouquery.main.grade.presenter.view.GradeView
import org.jetbrains.anko.support.v4.toast
import android.support.v7.app.AlertDialog
import com.example.xiyouquery.main.grade.data.protocol.GradeList
import com.example.xiyouquery.main.grade.data.protocol.GradeMenu
import kotlinx.android.synthetic.main.fragment_grade.*
import org.jetbrains.anko.sdk25.coroutines.onClick


/**
 * Created by 江婷婷 on 2018/2/25.
 */
class GradeFragment : BaseMvpFragment<GradePresenter>(), GradeView {

    private lateinit var fgContext: Context
    private var yearNum = 1
    private var termNum = 0

    fun getGradeFragment(context: Context) : GradeFragment {
        fgContext = context
        return this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPresenter = GradePresenter()
        mPresenter.mView = this

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_grade, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mPresenter.getGradeMenu()

    }

    override fun onGetGradeMenuResult() {
        toast("on get grade menu result")
        mXNBt.text = GradeMenu.year[yearNum]
        mXNBt.onClick {
            showDialog(GradeMenu.year, "请选择学年",
                    DialogInterface.OnClickListener { _, i ->
                        yearNum = i
                        mXNBt.text = GradeMenu.year[yearNum]
                    },  fgContext)
        }
        mXQBt.text = GradeMenu.term[termNum]
        mXQBt.onClick {
            showDialog(GradeMenu.term, "请选择学期",
                    DialogInterface.OnClickListener { _, i ->
                        termNum = i
                        mXQBt.text = GradeMenu.term[termNum]
                    }, fgContext)
        }
        mQueryBt.onClick {
            mPresenter.getGrade(mXNBt.text.toString(), mXQBt.text.toString())
        }



    }

    override fun onGetGradeResult() {

        var grade = ""
        for(g in GradeList.allGrade) {
            grade += "${g.toString()} \n"
        }
        if (grade == "") {
            grade = "本学期暂时没有成绩"
        }
        if (GradeMenu.term.isEmpty() && GradeMenu.year.isEmpty()) {
            grade = "请先评价"
        }
        mTx.text = grade
    }


    private fun showDialog(choices: List<String>, title: String, listener: DialogInterface.OnClickListener, context: Context) {
        val count = choices.size
        val items = arrayOfNulls<String>(count)
        for (i in 1..count) {
            items[i - 1] = choices[i - 1]
        }
        val builder = AlertDialog.Builder(context)
        builder.setTitle(title)
        builder.setItems(items, listener)
        builder.show()
    }
}