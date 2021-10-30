@file:Suppress("TooManyFunctions")

package com.mgg.baselib

import android.annotation.SuppressLint
import android.app.Activity
import android.app.ActivityManager
import android.app.Application
import android.content.Context
import android.content.Context.ACTIVITY_SERVICE
import android.content.ContextWrapper
import android.content.pm.PackageManager
import android.os.Build
import android.os.Process.myPid
import android.util.TypedValue
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.RequiresApi

@Suppress("unused")
fun Context.asActivity(): Activity? {
    var context = this
    while (context !is Activity && context is ContextWrapper) {
        context = context.baseContext
    }
    return context as? Activity
}

@Suppress("unused")
fun Context.isMainProcess(): Boolean {
    val am = this.getSystemService(ACTIVITY_SERVICE) as ActivityManager
    val runningApp = am.runningAppProcesses
    return if (runningApp == null) {
        false
    } else {
        val var3: Iterator<*> = runningApp.iterator()
        var info: ActivityManager.RunningAppProcessInfo
        do {
            if (!var3.hasNext()) {
                return false
            }
            info = var3.next() as ActivityManager.RunningAppProcessInfo
        } while (info.pid != myPid())
        this.packageName == info.processName
    }
}

@Suppress("unused")
fun Context.isPatchProcess(): Boolean {
    val am = this.getSystemService(ACTIVITY_SERVICE) as ActivityManager
    val runningApp = am.runningAppProcesses
    return if (runningApp == null) {
        false
    } else {
        val var3: Iterator<*> = runningApp.iterator()
        var info: ActivityManager.RunningAppProcessInfo
        do {
            if (!var3.hasNext()) {
                return false
            }
            info = var3.next() as ActivityManager.RunningAppProcessInfo
        } while (info.pid != myPid())
        info.processName.endsWith("patch")
    }
}

/**
 * 判断Activity 是否可用
 */
@SuppressLint("ObsoleteSdkInt")
@Suppress("unused")
fun Context.isActivityAvailable(): Boolean {
    if (this !is Activity) return false
    if (this.isFinishing) return false
    return !(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1 && this.isDestroyed)
}

/**
 * 反射一下主线程获取一下上下文
 */
@Suppress("unused")
val contextReflex: Application? by lazy {
    try {
        @SuppressLint("PrivateApi")
        val activityThreadClass = Class.forName("android.app.ActivityThread")

        @SuppressLint("DiscouragedPrivateApi")
        val currentApplicationMethod = activityThreadClass.getDeclaredMethod("currentApplication")
        currentApplicationMethod.isAccessible = true
        currentApplicationMethod.invoke(null) as Application
    } catch (ex: Exception) {
        return@lazy null
    }
}

@Suppress("unused")
val application: Application?
    @SuppressLint("PrivateApi", "DiscouragedPrivateApi")
    get() = try {
        val activityThread = Class.forName("android.app.ActivityThread")
        val currentApplication = activityThread.getDeclaredMethod("currentApplication")
        val currentActivityThread = activityThread.getDeclaredMethod("currentActivityThread")
        val current = currentActivityThread.invoke(null as Any?)
        val app = currentApplication.invoke(current)
        app as Application
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }

/**
 * 得到目标界面 Class
 */
@Suppress("unused")
fun String?.getTargetClass(): Class<*>? {
    var clazz: Class<*>? = null
    try {
        if (!this.isNullOrEmpty()) {
            clazz = Class.forName(this)
        }
    } catch (e: ClassNotFoundException) {
        e.printStackTrace()
    }
    return clazz
}

@Suppress("unused")
fun Activity.hasPermission(permission: String): Boolean {
    return !isMarshmallow() || isGranted(permission)
}

@SuppressLint("AnnotateVersionCheck")
fun isMarshmallow(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M

@RequiresApi(Build.VERSION_CODES.M)
fun Activity.isGranted(permission: String): Boolean {
    return this.checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED
}

@Suppress("unused")
fun Context.dpToPx(value: Float): Float {
    return resources.displayMetrics.let { metrics ->
        TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, metrics)
    }
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

@Suppress("unused")
fun Context.showKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
}
