package com.app.flutter

import android.content.Context
import android.support.v4.app.Fragment
import android.widget.Toast

fun Fragment.showShort(content: String?): Toast {
    val toast = Toast.makeText(this.activity, content, Toast.LENGTH_SHORT);
    toast.show()
    return toast
}

fun Fragment.showLong(content: String): Toast {
    val toast = Toast.makeText(this.activity, content, Toast.LENGTH_LONG);
    toast.show()
    return toast
}

fun Context.showShort(content: String?): Toast {
    val toast = Toast.makeText(this, content, Toast.LENGTH_SHORT);
    toast.show()
    return toast
}

fun Context.showLong(content: String): Toast {
    val toast = Toast.makeText(this, content, Toast.LENGTH_LONG);
    toast.show()
    return toast
}