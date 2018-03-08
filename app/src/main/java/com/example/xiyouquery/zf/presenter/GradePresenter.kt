package com.example.xiyouquery.zf.presenter

import com.example.xiyouquery.base.ext.execute
import com.example.xiyouquery.base.presenter.BasePresenter
import com.example.xiyouquery.base.rx.BaseSubscriber
import com.example.xiyouquery.main.data.protocol.StudentInfo
import com.example.xiyouquery.zf.data.protocol.Grade
import com.example.xiyouquery.zf.data.protocol.GradeData
import com.example.xiyouquery.zf.data.protocol.Hidden
import com.example.xiyouquery.zf.presenter.view.GradeView
import com.example.xiyouquery.zf.service.impl.GradeServiceImpl
import okhttp3.ResponseBody
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import rx.Observable
import rx.functions.Func1

/**
 * Created by 江婷婷 on 2018/3/3.
 */
class GradePresenter : BasePresenter<GradeView>() {

    fun getGradeMenu() {
        GradeServiceImpl().getGradeMenu(StudentInfo.id, StudentInfo.name)
                .flatMap(Func1<ResponseBody, Observable<GradeData>> { t ->

                    val doc: Document = Jsoup.parse(t.string())
                    Hidden.__EVENTTARGET = doc.getElementsByTag("input")[0].attr("value")
                    Hidden.__EVENTARGUMENT = doc.getElementsByTag("input")[1].attr("value")
                    Hidden.__VIEWSTATE = doc.getElementsByTag("input")[2].attr("value")

                    val year = doc.select("[name=ddlXN][id=ddlXN]")[0].text().split(" ") as ArrayList<String>
                    val term = doc.select("[name=ddlXQ][id=ddlXQ]")[0].text().split(" ") as ArrayList<String>

                    val semester = arrayListOf<String>()
                    var count = year.size
                    while (count > 0) {
                        semester.add("大$count 第2学期")
                        semester.add("大$count 第1学期")
                        count--
                    }
                    return@Func1 Observable.just(GradeData(year, term, semester))

                })
                .execute(object : BaseSubscriber<GradeData>() {
                    override fun onNext(t: GradeData) {
                        mView.onGetGradeMenuResult(t.year, t.term, t.semester)
                        if (t.term.isEmpty() && t.year.isEmpty()) {
                            mView.onGradeError("请先评价")
                        }
                    }
                })
    }

    fun getGrade(xn: String, xq: String) {
        GradeServiceImpl().getGrade(xn, xq)
                .flatMap(Func1<ResponseBody, Observable<ArrayList<Grade>>> { t ->
                    val doc: Document = Jsoup.parse(t.string())
                    Hidden.__VIEWSTATE = doc.getElementsByTag("input")[2].attr("value")
                    val table = doc.getElementById("divNotPs").getElementsByTag("tr")
                    val grades: ArrayList<Grade> = arrayListOf()
                    for (i in 1 until table.size) {
                        val td = table[i].select("td")
                        if (td.size > 8) {
                            val grade = Grade()
                            grade.name = td[3].text()
                            grade.property = td[4].text()
                            grade.credit = td[6].text()
                            grade.point = td[7].text()
                            grade.grade = td[8].text()
                            grades.add(grade)
                        }
                    }
                    return@Func1 Observable.just(grades)

                })
                .execute(object : BaseSubscriber<ArrayList<Grade>>() {
                    override fun onNext(t: ArrayList<Grade>) {
                        if (t.isEmpty()) {
                            mView.onGradeError("本学期暂时没有成绩")
                        } else {
                            mView.onNotifyGrade(t)
                        }
                    }
                })
    }

}