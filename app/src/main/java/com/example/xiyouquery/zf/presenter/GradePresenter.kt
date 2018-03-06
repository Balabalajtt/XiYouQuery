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

/**
 * Created by 江婷婷 on 2018/3/3.
 */
class GradePresenter : BasePresenter<GradeView>() {

    fun getGradeMenu() {
        GradeServiceImpl().getGradeMenu(StudentInfo.id, StudentInfo.name)
                .execute(object : BaseSubscriber<ResponseBody>() {
                    override fun onNext(t: ResponseBody) {

                        parseGradeMenu(t.string())
                        mView.onGetGradeMenuResult(GradeData.year, GradeData.term, GradeData.semester)

                    }
                })
    }

    fun getGrade(xn: String, xq: String) {
        GradeServiceImpl().getGrade(xn, xq)
                .execute(object : BaseSubscriber<ResponseBody>() {
                    override fun onNext(t: ResponseBody) {

                        parseGrade(t.string())

                        if (GradeData.term.isEmpty() && GradeData.year.isEmpty()) {
                            mView.onGradeError("请先评价")
                        } else if (GradeData.allGrade.isEmpty()) {
                            mView.onGradeError("本学期暂时没有成绩")
                        } else {
                            mView.onNotifyGrade()
                        }
                    }
                })
    }

    private fun parseGradeMenu(response: String) {
        val doc: Document = Jsoup.parse(response)
        Hidden.__EVENTTARGET = doc.getElementsByTag("input")[0].attr("value")
        Hidden.__EVENTARGUMENT = doc.getElementsByTag("input")[1].attr("value")
        Hidden.__VIEWSTATE = doc.getElementsByTag("input")[2].attr("value")

        GradeData.year = doc.select("[name=ddlXN][id=ddlXN]")[0].text().split(" ") as ArrayList<String>
        GradeData.term = doc.select("[name=ddlXQ][id=ddlXQ]")[0].text().split(" ") as ArrayList<String>

        var count = GradeData.year.size
        while (count > 0) {
            GradeData.semester.add("大$count 第2学期")
            GradeData.semester.add("大$count 第1学期")
            count--
        }

    }

    private fun parseGrade(response: String) {
        val doc: Document = Jsoup.parse(response)
        Hidden.__VIEWSTATE = doc.getElementsByTag("input")[2].attr("value")
        val table = doc.getElementById("divNotPs").getElementsByTag("tr")
        GradeData.allGrade.clear()
        for (i in 1 until table.size) {
            val td = table[i].select("td")
            if (td.size > 8) {
                val grade = Grade()
                grade.name = td[3].text()
                grade.property = td[4].text()
                grade.credit = td[6].text()
                grade.point = td[7].text()
                grade.grade = td[8].text()
                GradeData.allGrade.add(grade)
            }
        }
    }

}