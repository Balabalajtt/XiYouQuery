package com.example.xiyouquery.main.grade.presenter

import android.util.Log
import com.example.xiyouquery.base.ext.execute
import com.example.xiyouquery.base.presenter.BasePresenter
import com.example.xiyouquery.base.rx.BaseSubscriber
import com.example.xiyouquery.login.data.protocol.StudentInfo
import com.example.xiyouquery.main.grade.data.protocol.Grade
import com.example.xiyouquery.main.grade.data.protocol.GradeList
import com.example.xiyouquery.main.grade.data.protocol.GradeMenu
import com.example.xiyouquery.main.grade.data.protocol.Hidden
import com.example.xiyouquery.main.grade.presenter.view.GradeView
import com.example.xiyouquery.main.grade.service.impl.GradeServiceImpl
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
                        mView.onGetGradeMenuResult()
                    }
                })
    }

    private fun parseGradeMenu(response: String) {
        val doc: Document = Jsoup.parse(response)
        Hidden.__EVENTTARGET = doc.getElementsByTag("input")[0].attr("value")
        Hidden.__EVENTARGUMENT = doc.getElementsByTag("input")[1].attr("value")
        Hidden.__VIEWSTATE = doc.getElementsByTag("input")[2].attr("value")

        Log.d("aaaaaaaaa", Hidden.__EVENTARGUMENT + Hidden.__EVENTTARGET + Hidden.__VIEWSTATE)

        GradeMenu.year = doc.select("[name=ddlXN][id=ddlXN]")[0].text().split(" ")
        GradeMenu.term = doc.select("[name=ddlXQ][id=ddlXQ]")[0].text().split(" ")

        var count = GradeMenu.year.size
        while (count > 0) {
            GradeMenu.semester.add("大$count 第2学期")
            GradeMenu.semester.add("大$count 第1学期")
//            GradeMenu.semester.add("$i 学年 第3学期")
            count--
        }

    }

    fun getGrade(xn: String, xq: String) {

        Log.d("aaaaaaaaa", "Impl上面")
        GradeServiceImpl().getGrade(xn, xq)
                .execute(object : BaseSubscriber<ResponseBody>() {
                    override fun onNext(t: ResponseBody) {
                        parseGrade(t.string())
                        mView.onGetGradeResult()
                    }
                })
    }

    private fun parseGrade(response: String) {

        val doc: Document = Jsoup.parse(response)
        Hidden.__VIEWSTATE = doc.getElementsByTag("input")[2].attr("value")
        val table = doc.getElementById("divNotPs").getElementsByTag("tr")
        GradeList.allGrade.clear()
        for (i in 1 until table.size) {
            val td = table[i].select("td")
            if (td.size > 8) {
                val grade = Grade()
                grade.name = td[3].text()
                grade.property = td[4].text()
                grade.credit = td[6].text()
                grade.point = td[7].text()
                grade.grade = td[8].text()
                Log.d("aaaaaaaaa", grade.toString())
                GradeList.allGrade.add(grade)
            }
        }
    }

}