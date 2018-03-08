package com.example.xiyouquery.zf.presenter

import com.example.xiyouquery.base.ext.execute
import com.example.xiyouquery.base.presenter.BasePresenter
import com.example.xiyouquery.base.rx.BaseSubscriber
import com.example.xiyouquery.main.data.protocol.StudentInfo
import com.example.xiyouquery.zf.presenter.view.CurriculumView
import com.example.xiyouquery.zf.service.impl.CurriculumServiceImpl
import okhttp3.ResponseBody
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import rx.Observable
import rx.functions.Func1

/**
 * Created by 江婷婷 on 2018/3/4.
 */
class CurriculumPresenter : BasePresenter<CurriculumView>() {
    fun getInitCurriculum() {
        CurriculumServiceImpl().getInitCurriculum(StudentInfo.id, StudentInfo.name)
                .flatMap(Func1<ResponseBody, Observable<ArrayList<String>>> { t ->
                    val doc: Document = Jsoup.parse(t.string())
                    val table = doc.getElementsByTag("table")[1].getElementsByTag("tr")
                    var i = 2
                    val allCurriculum = arrayListOf<String>()
                    while(i < table.size) {
                        val td = table[i].getElementsByTag("td")
                        for (j in 0 until td.size) {
                            if (td[j].text() == "") {
                                allCurriculum.add("")
                            } else if (td[j].text().length > 4){
                                allCurriculum.add(td[j].text())
                            }
                        }
                        i+=2
                    }

                    return@Func1 Observable.just(allCurriculum)

                })
                .execute(object : BaseSubscriber<ArrayList<String>>() {
                    override fun onNext(t: ArrayList<String>) {
                        mView.onGetInit(t)
                    }
                })
    }

}