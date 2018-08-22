package com.app.flutter.bean

data class SampleData(val code: Int, val msg: Msg) {

    data class Msg(val news: News) {

        data class News(val data: ArrayList<Data>, val total: Int) {

            data class Data(val author: String, val authorImg: String, val commCount: Int, val detailUrl: String,
                            val id: String, val newsType: String, val summary: String, val thumb: String,
                            val timeStr: String, val title: String)

        }

    }

}