package com.app.flutter.http

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    private val BASE_URL = "http://osc.yubo725.top"

    private var okHttpBuilder: OkHttpClient.Builder? = null

    private var retrofit: Retrofit? = null
    private var okHttpClient: OkHttpClient? = null

    fun <K> createApi(cls: Class<K>?): K? {
        return getRetrofit()?.create(cls)
    }

    private fun getRetrofit(): Retrofit? {
        if (retrofit == null) {

            synchronized(RetrofitHelper::class.java) {

                if (retrofit == null) {
                    okHttpBuilder = OkHttpClient.Builder()
                    okHttpClient = okHttpBuilder?.build()
                    retrofit = Retrofit.Builder()
                            .baseUrl(BASE_URL)
                            .client(okHttpClient)
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build()
                }

            }

        }
        return retrofit
    }

}