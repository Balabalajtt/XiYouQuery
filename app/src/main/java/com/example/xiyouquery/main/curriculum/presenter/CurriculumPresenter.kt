package com.example.xiyouquery.main.curriculum.presenter

import android.util.Log
import com.example.xiyouquery.base.ext.execute
import com.example.xiyouquery.base.presenter.BasePresenter
import com.example.xiyouquery.base.rx.BaseSubscriber
import com.example.xiyouquery.login.data.protocol.StudentInfo
import com.example.xiyouquery.main.curriculum.data.protocol.Curriculum
import com.example.xiyouquery.main.curriculum.presenter.view.CurriculumView
import com.example.xiyouquery.main.curriculum.service.impl.CurriculumServiceImpl
import okhttp3.ResponseBody
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

/**
 * Created by 江婷婷 on 2018/3/4.
 */
class CurriculumPresenter : BasePresenter<CurriculumView>() {
    fun getInitCurriculum() {
        CurriculumServiceImpl().getInitCurriculum(StudentInfo.id, StudentInfo.name)
                .execute(object : BaseSubscriber<ResponseBody>() {
                    override fun onNext(t: ResponseBody) {

                        parseInit(t.string())
                        mView.onGetInit()
                    }
                })
    }

    private fun parseInit(response: String) {
        val doc: Document = Jsoup.parse(response)
        val table = doc.getElementsByTag("table")[1].getElementsByTag("tr")
        var i = 2
        while(i < table.size) {
            val td = table[i].getElementsByTag("td")
            for (j in 0 until td.size) {
                if (td[j].text() == "") {
                    Log.d("aaaa", "aaaaa")
                    Curriculum.allClass.add("")
                } else if (td[j].text().length > 4){
                    Curriculum.allClass.add(td[j].text())
                }
            }
            i+=2
        }
    }
}