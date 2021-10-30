package com.mgg.baselib

import android.content.Context

@Suppress("unused")
inline val Context.vibrator: android.os.Vibrator
    get() = getSystemService(Context.VIBRATOR_SERVICE) as android.os.Vibrator

@Suppress("unused")
inline val Context.layoutInflater: android.view.LayoutInflater
    get() = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as android.view.LayoutInflater
