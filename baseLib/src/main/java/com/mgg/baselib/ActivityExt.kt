package com.mgg.baselib

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.view.View

@Suppress("unused")
fun Activity.isTaskRoot(): Boolean {
    if (!isTaskRoot) {
        val intent = intent
        val action = intent.action
        if (intent.hasCategory(Intent.CATEGORY_LAUNCHER) && Intent.ACTION_MAIN == action) {
            finish()
            return false
        }
    }
    return true
}

@Suppress("unused")
fun Activity.hideKeyboard() {
    hideKeyboard(currentFocus ?: View(this))
}

@Suppress("unused")
fun Activity.showKeyboard() {
    showKeyboard(currentFocus ?: View(this))
}

@Suppress("unused")
fun <T> Bundle.put(key: String, value: T): Unit = when (value) {
    is Boolean -> putBoolean(key, value)
    is String -> putString(key, value)
    is Int -> putInt(key, value)
    is Short -> putShort(key, value)
    is Long -> putLong(key, value)
    is Byte -> putByte(key, value)
    is ByteArray -> putByteArray(key, value)
    is Char -> putChar(key, value)
    is CharArray -> putCharArray(key, value)
    is CharSequence -> putCharSequence(key, value)
    is Float -> putFloat(key, value)
    is Bundle -> putBundle(key, value)
    is Parcelable -> putParcelable(key, value)
    // is Serializable -> putSerializable(key, value)
    else -> throw IllegalStateException("Type of property $key is not supported")
}
