package com.app.flutter.base

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BasePresenter<V : IView> : IPresenter<V> {

    var mRootView: V? = null
        private set

    private var mDisposable = CompositeDisposable()

    override fun attachView(view: V) {
        this.mRootView = view
    }

    override fun detachView() {
        mRootView = null
        if (!mDisposable.isDisposed) {
            mDisposable.clear()
        }
    }

    fun addSubscription(disposable: Disposable) {
        mDisposable.add(disposable)
    }

}