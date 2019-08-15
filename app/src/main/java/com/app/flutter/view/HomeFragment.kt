package com.app.flutter.view

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import android.view.View
import com.app.flutter.model.DataModel
import com.app.flutter.presenter.DataPresenter
import io.flutter.facade.FlutterFragment
import io.flutter.plugin.common.MethodChannel
import io.flutter.view.FlutterView

class HomeFragment : FlutterFragment(), DataView {

    private val dialog by lazy {
        ProgressDialog(activity)
    }

    private var dataJson = ""

    override fun getDataSuccess(dataJson: String) {
        dialog.dismiss()
        this.dataJson = dataJson

    }

    override fun getDataFail(failMsg: String) {
        dialog.dismiss()
    }

    private val presenter by lazy {
        DataPresenter(DataModel())
    }

    companion object {

        val PULL_CHANNEL = "sample.flutter.io/pull_home"

        fun newInstance(route: String): HomeFragment {

            val fragment = HomeFragment()
            val args = Bundle()
            args.putString(FlutterFragment.ARG_ROUTE, route)
            fragment.arguments = args
            return fragment

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun getDataJson(): String {
        return dataJson
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attachView(this)

        dialog.show()
        presenter.getData(1)

        MethodChannel(view as FlutterView, PULL_CHANNEL).setMethodCallHandler { methodCall, result ->
            run {
                if (methodCall.method.equals("getDataJson")) {
                    val json = getDataJson()
                    Log.e("fuck", "json: $json")
                    result.success(json)
                } else {
                    result.notImplemented()
                }
            }
        }

    }

}