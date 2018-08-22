package com.app.flutter.view

import android.os.Bundle
import android.view.View
import io.flutter.facade.FlutterFragment
import io.flutter.plugin.common.EventChannel
import io.flutter.view.FlutterView

class DashboardFragment : FlutterFragment() {

    companion object {

        val PUSH_CHANNEL = "sample.flutter.io/push_dashboard"

        fun newInstance(route: String): DashboardFragment {

            val fragment = DashboardFragment()
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
                events?.success("DashboardFragment")
            }

            override fun onCancel(p0: Any?) {

            }
        })

    }

}