package com.app.flutter

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.FrameLayout
import io.flutter.facade.Flutter
import io.flutter.plugin.common.EventChannel
import io.flutter.plugin.common.MethodChannel

class MainActivity : AppCompatActivity() {

    companion object {

        val PUSH_CHANNEL = "sample.flutter.io/push"
        val PULL_CHANNEL = "sample.flutter.io/pull"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val flutterView = Flutter.createView(this, lifecycle, "route1")

        addContentView(flutterView, FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));

        EventChannel(flutterView, PUSH_CHANNEL).setStreamHandler(object : EventChannel.StreamHandler {
            override fun onListen(p0: Any?, events: EventChannel.EventSink?) {
                events?.success(getName())
            }

            override fun onCancel(p0: Any?) {

            }
        })

        MethodChannel(flutterView, PULL_CHANNEL).setMethodCallHandler { methodCall, result ->
            run {
                if (methodCall.method.equals("refresh")) {
                    refresh()
                    result.success("")
                } else {
                    result.notImplemented()
                }
            }
        }

    }

    fun getName(): String? = "flutter_library"

    fun refresh() {
        showShort("refresh")
    }

}