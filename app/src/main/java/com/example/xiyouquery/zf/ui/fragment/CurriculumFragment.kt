package com.example.xiyouquery.zf.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.xiyouquery.R
import com.example.xiyouquery.base.ui.fragement.BaseMvpFragment
import com.example.xiyouquery.zf.presenter.CurriculumPresenter
import com.example.xiyouquery.zf.presenter.view.CurriculumView
import com.example.xiyouquery.zf.ui.adapter.CurriculumGridAdapter
import kotlinx.android.synthetic.main.fragment_curriculum.*


/**
 * Created by 江婷婷 on 2018/2/25.
 */
class CurriculumFragment : BaseMvpFragment<CurriculumPresenter>(), CurriculumView {

    private lateinit var adapter: CurriculumGridAdapter
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
        adapter = CurriculumGridAdapter(fgContext)
        mGridView.adapter = adapter
        mPresenter.getInitCurriculum()

    }

    override fun onGetInit(allCurriculum: ArrayList<String>) {
        adapter.setData(allCurriculum)
        adapter.notifyDataSetChanged()
    }

}