package com.mgg.baselib

import android.view.View
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * var isProgressVisible by progressBar.isVisible(keepBounds = false)
 */
@Suppress("unused")
fun View.isVisible(keepBounds: Boolean): ReadWriteProperty<Any, Boolean> =
    object : ReadWriteProperty<Any, Boolean> {
        override fun getValue(
            thisRef: Any,
            property: KProperty<*>
        ): Boolean = visibility == View.VISIBLE

        override fun setValue(
            thisRef: Any,
            property: KProperty<*>,
            value: Boolean
        ) {
            visibility = when {
                value -> View.VISIBLE
                keepBounds -> View.INVISIBLE
                else -> View.GONE
            }
        }
    }
