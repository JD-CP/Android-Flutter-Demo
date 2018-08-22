package com.app.flutter.presenter

import com.app.flutter.base.BasePresenter
import com.app.flutter.http.DataTransformer
import com.app.flutter.model.DataModel
import com.app.flutter.view.DataView
import com.google.gson.Gson

class DataPresenter(private val dataModel: DataModel) : BasePresenter<DataView>() {

    fun getData(page: Int) {
        dataModel.getMovies(page)
                ?.compose(DataTransformer.switchSchedulers())
                ?.subscribe({ sampleData ->
                    mRootView?.apply {
                        getDataSuccess(Gson().toJson(sampleData))
                    }
                }, { t ->
                    mRootView?.apply {
                        getDataFail(t.message.toString())
                    }
                })

    }

}