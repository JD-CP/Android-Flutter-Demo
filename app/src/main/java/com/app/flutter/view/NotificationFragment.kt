package com.app.flutter.view

import android.os.Bundle
import android.view.View
import io.flutter.facade.FlutterFragment
import io.flutter.plugin.common.EventChannel
import io.flutter.view.FlutterView

class NotificationFragment : FlutterFragment() {

    companion object {

        val PUSH_CHANNEL = "sample.flutter.io/push_notification"

        fun newInstance(route: String): NotificationFragment {

            val fragment = NotificationFragment()
            val args = Bundle()
            args.putString(FlutterFragment.ARG_ROUTE, route)
            fragment.arguments = args
            return fragment

        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        EventChannel(view as FlutterView, PUSH_CHANNEL).setStreamHandler(object : EventChannel.StreamHandler {
            override fun onListen(p0: Any?, events: EventChannel.EventSink?) {
                events?.success("NotificationFragment")
            }

            override fun onCancel(p0: Any?) {

            }
        })

    }

}