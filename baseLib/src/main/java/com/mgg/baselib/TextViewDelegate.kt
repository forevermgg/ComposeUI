package com.mgg.baselib

import android.widget.TextView
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/*class CustomView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {
    var title: String
        get() = tvTitle.text.toString()
        set(value) {
            tvTitle.text = value
        }
    var subtitle: String
        get() = tvSubtitle.text.toString()
        set(value) {
            tvSubtitle.text = value
        }
    var description: String
        get() = tvDescription.text.toString()
        set(value) {
            tvDescription.text = value
        }
    init {
        inflate(context, R.layout.custom_view, this)
    }
}*/
/***
 *
class CustomView @JvmOverloads constructor(
context: Context,
attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {

init {
inflate(context, R.layout.custom_view, this)
}

var title by tvTitle.text()
var subtitle by tvSubtitle.text()
var description by tvDescription.text()
}
 */
fun TextView.text(): ReadWriteProperty<Any, String> =
    object : ReadWriteProperty<Any, String> {
        override fun getValue(
            thisRef: Any,
            property: KProperty<*>
        ): String = text.toString()

        override fun setValue(thisRef: Any, property: KProperty<*>, value: String) {
            text = value
        }
    }
