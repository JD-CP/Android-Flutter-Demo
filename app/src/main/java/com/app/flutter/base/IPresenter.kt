package com.app.flutter.base

interface IPresenter<in V : IView> {

    fun attachView(view: V)

    fun detachView()

}