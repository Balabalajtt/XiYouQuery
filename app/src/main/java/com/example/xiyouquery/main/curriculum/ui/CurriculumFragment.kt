package com.example.xiyouquery.main.curriculum.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.xiyouquery.R
import com.example.xiyouquery.base.ui.fragement.BaseMvpFragment
import com.example.xiyouquery.main.curriculum.data.protocol.Curriculum
import com.example.xiyouquery.main.curriculum.presenter.CurriculumPresenter
import com.example.xiyouquery.main.curriculum.presenter.view.CurriculumView
import com.example.xiyouquery.main.grade.data.protocol.GradeList
import kotlinx.android.synthetic.main.fragment_curriculum.*
import org.jetbrains.anko.support.v4.toast


/**
 * Created by 江婷婷 on 2018/2/25.
 */
class CurriculumFragment : BaseMvpFragment<CurriculumPresenter>(), CurriculumView {

    private lateinit var adapter: MyAdapter

    override fun onGetInit() {
        adapter.notifyDataSetChanged()
        toast("on get init")
    }

    private lateinit var fgContext: Context

    fun getCurriculumFragment(context: Context): CurriculumFragment {
        fgContext = context
        return this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPresenter = CurriculumPresenter()
        mPresenter.mView = this

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_curriculum, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = MyAdapter(fgContext, Curriculum.allClass)
        mGridView.adapter = adapter

        mPresenter.getInitCurriculum()

    }
}