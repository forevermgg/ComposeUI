package com.mgg.baselib

import android.content.Context
import androidx.appcompat.app.AlertDialog
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resumeWithException

@ExperimentalCoroutinesApi
@Suppress("unused")
suspend fun Context.alert(title: String, message: String): Boolean =
    suspendCancellableCoroutine { continuation ->
        AlertDialog.Builder(this)
            .setNegativeButton("No") { dialog, _ ->
                dialog.dismiss()
                continuation.resume(false, onCancellation = {
                    continuation.resumeWithException(it)
                })
            }.setPositiveButton("Yes") { dialog, _ ->
                dialog.dismiss()
                continuation.resume(true, onCancellation = {
                    continuation.resumeWithException(it)
                })
            }.setTitle(title).setMessage(message)
            .setOnCancelListener {
                continuation.resume(false, onCancellation = {
                    continuation.resumeWithException(it)
                })
            }.create()
            .also { dialog ->
                continuation.invokeOnCancellation {
                    dialog.dismiss()
                }
            }.show()
    }
