package com.app.flutter.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

abstract class BaseMVPActivity : AppCompatActivity(), IView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(createView())
        viewCreated()
    }

    /**
     * 加载布局
     */
    abstract fun createView(): Int

    /**
     * 布局加载完成，初始化数据
     */
    abstract fun viewCreated()

}