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
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import com.example.xiyouquery.main.grade.data.protocol.GradeList
import com.example.xiyouquery.main.grade.data.protocol.GradeMenu
import kotlinx.android.synthetic.main.fragment_grade.*
import org.jetbrains.anko.sdk25.coroutines.onLongClick


/**
 * Created by 江婷婷 on 2018/2/25.
 */
class GradeFragment : BaseMvpFragment<GradePresenter>(), GradeView {

    private lateinit var fgContext: Context
    private lateinit var adapter: GradeRVAdapter
    private var yearNum = 0
    private var termNum = 0
    private var semesterNum = 0

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

        adapter = GradeRVAdapter(fgContext)
        mRecyclerView.layoutManager = LinearLayoutManager(fgContext)
        mRecyclerView.adapter = adapter

    }

    override fun onGetGradeMenuResult() {
        mTimeTv.text = "${GradeMenu.year[yearNum]}学年 第${GradeMenu.term[termNum]}学期"
        mTimeTv.onLongClick {
            showDialog(GradeMenu.semester, "请选择学期",
                    DialogInterface.OnClickListener { _, i ->
                        semesterNum = i
                        yearNum = i / 2
                        termNum = 1 - i % 2
                        mTimeTv.text = "${GradeMenu.year[yearNum]}学年 第${GradeMenu.term[termNum]}学期"
                        mPresenter.getGrade(GradeMenu.year[yearNum], GradeMenu.term[termNum])
                    },  fgContext)
        }

        mPresenter.getGrade(GradeMenu.year[yearNum], GradeMenu.term[termNum])

    }

    override fun onGetGradeResult() {

        var grade = ""
        if (GradeList.allGrade.isEmpty()) {
            grade = "本学期暂时没有成绩"
        }
        if (GradeMenu.term.isEmpty() && GradeMenu.year.isEmpty()) {
            grade = "请先评价"
        }
        if (grade != "") {
            mTx.text = grade
            mTx.visibility = View.VISIBLE
        } else {
            mTx.visibility = View.GONE
        }

        adapter.notifyDataSetChanged()
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