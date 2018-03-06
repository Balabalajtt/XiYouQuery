package com.example.xiyouquery.zf.data.protocol

/**
 * Created by 江婷婷 on 2018/3/3.
 */
class Grade {
    lateinit var name: String
    lateinit var property: String
    lateinit var credit: String
    lateinit var point: String
    lateinit var grade: String

    override fun toString(): String {
        return "$name  $property  $credit  $point  $grade"
    }
}