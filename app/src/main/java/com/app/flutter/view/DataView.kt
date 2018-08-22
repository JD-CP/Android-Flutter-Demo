package com.app.flutter.view

import com.app.flutter.base.IView

interface DataView : IView {

    fun getDataSuccess(movieJson: String)
    fun getDataFail(failMsg: String)

}