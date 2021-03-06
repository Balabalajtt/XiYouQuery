package com.example.xiyouquery.zf.ui.fragment

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.xiyouquery.R
import com.example.xiyouquery.base.ui.fragement.BaseMvpFragment
import com.example.xiyouquery.zf.presenter.GradePresenter
import com.example.xiyouquery.zf.presenter.view.GradeView
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import com.example.xiyouquery.zf.data.protocol.Grade
import com.example.xiyouquery.zf.ui.adapter.GradeRVAdapter
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

    override fun onGetGradeMenuResult(yearList: ArrayList<String>, termList: ArrayList<String>, semesterList: ArrayList<String>) {
        val title = "${yearList[yearNum]}学年 第${termList[termNum]}学期"
        mTimeTv.text = title
        mTimeTv.onLongClick {
            showDialog(semesterList, "请选择学期",
                    DialogInterface.OnClickListener { _, i ->
                        semesterNum = i
                        yearNum = i / 2
                        termNum = 1 - i % 2
                        val title = "${yearList[yearNum]}学年 第${termList[termNum]}学期"
                        mTimeTv.text = title
                        mPresenter.getGrade(yearList[yearNum], termList[termNum])
                    },  fgContext)
        }

        mPresenter.getGrade(yearList[yearNum], termList[termNum])

    }

    override fun onGradeError(message: String) {
        mTx.visibility = View.VISIBLE
        mTx.text = message
        adapter.setData(arrayListOf())
        adapter.notifyDataSetChanged()
    }

    override fun onNotifyGrade(grades: ArrayList<Grade>) {
        mTx.visibility = View.GONE
        adapter.setData(grades)
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