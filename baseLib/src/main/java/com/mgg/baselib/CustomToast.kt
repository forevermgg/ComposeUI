package com.mgg.baselib

import android.app.Activity
import android.content.Context
import android.widget.Toast

@Suppress("unused")
fun Activity.toast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}

@Suppress("unused")
fun Context.toast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}
