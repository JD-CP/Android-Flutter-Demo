package com.app.flutter.model

import com.app.flutter.api.DataApi
import com.app.flutter.bean.SampleData
import com.app.flutter.http.RetrofitHelper
import io.reactivex.Observable

class DataModel() {

    private val dataApi by lazy {
        RetrofitHelper.createApi(DataApi::class.java)
    }


    fun getMovies(page: Int): Observable<SampleData>? {
        return dataApi?.getFirstHomeData(page, 10);
    }

}