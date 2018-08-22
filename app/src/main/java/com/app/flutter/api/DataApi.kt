package com.app.flutter.api

import com.app.flutter.bean.SampleData
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface DataApi {

    @GET("/news/list")
    fun getFirstHomeData(@Query("pageIndex") pageIndex: Int, @Query("pageSize") pageSize: Int): Observable<SampleData>?

}