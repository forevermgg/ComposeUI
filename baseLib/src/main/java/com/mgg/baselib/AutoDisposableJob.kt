package com.mgg.baselib

import android.annotation.SuppressLint
import android.os.Build
import android.view.View
import kotlinx.coroutines.Job

class AutoDisposableJob(private val view: View, wrapped: Job) : View.OnAttachStateChangeListener,
    Job by wrapped {
    init {
        if (isAttached()) {
            view.addOnAttachStateChangeListener(this)
        } else {
            cancel()
        }

        // CF中调用Job的invokeOnCompletion(completionHandler)方法
        invokeOnCompletion {
            view.post {
                view.removeOnAttachStateChangeListener(this)
            }
        }
    }

    override fun onViewAttachedToWindow(v: View?) = Unit

    override fun onViewDetachedFromWindow(v: View?) {
        this.cancel()
        view.removeOnAttachStateChangeListener(this)
    }

    @SuppressLint("ObsoleteSdkInt")
    private fun isAttached(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT &&
                view.isAttachedToWindow || view.windowToken != null
    }
}

@Suppress("unused")
fun Job.autoDispose(view: View) = AutoDisposableJob(view, this)
