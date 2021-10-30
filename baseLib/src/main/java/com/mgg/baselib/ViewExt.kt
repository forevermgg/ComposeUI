package com.mgg.baselib

import android.content.Context
import android.content.res.Resources
import android.graphics.Rect
import android.util.TypedValue
import android.view.TouchDelegate
import android.view.View
import android.widget.Checkable
import androidx.core.view.isGone
import androidx.core.view.isInvisible
import androidx.core.view.isVisible

// 屏幕宽高
@Suppress("unused")
val width
    get() = Resources.getSystem().displayMetrics.widthPixels

@Suppress("unused")
val height
    get() = Resources.getSystem().displayMetrics.heightPixels

// 状态栏高度
@Suppress("unused")
val statusBarHeight: Int
    get() {
        val resources = Resources.getSystem()
        val id = resources.getIdentifier("status_bar_height", "dimen", "android")
        return resources.getDimensionPixelSize(id)
    }

/**
 * 规定时间内只允许单次点击
 */
@Suppress("unused")
inline fun <T : View> T.singleClick(time: Long = 800, crossinline block: (T) -> Unit) {
    setOnClickListener {
        val currentTimeMillis = System.currentTimeMillis()
        if (currentTimeMillis - lastClickTime > time || this is Checkable) { // 不阻止实现Checkable(如CheckBox)的View连点
            lastClickTime = currentTimeMillis
            block(this)
        }
    }
}

/**
 * 规定时间内只允许单次点击
 * 兼容点击事件设置为this的情况
 */
@Suppress("unused")
fun <T : View> T.singleClick(onClickListener: View.OnClickListener, time: Long = 800) {
    setOnClickListener {
        val currentTimeMillis = System.currentTimeMillis()
        if (currentTimeMillis - lastClickTime > time || this is Checkable) { // 不阻止实现Checkable(如CheckBox)的View连点
            lastClickTime = currentTimeMillis
            onClickListener.onClick(this)
        }
    }
}

var <T : View> T.lastClickTime
    set(value) = setTag(1766613352, value)
    get() = getTag(1766613352) as? Long ?: 0

@Suppress("unused")
fun View.visible() {
    if (visibility != View.VISIBLE) {
        visibility = View.VISIBLE
    }
}

@Suppress("unused")
fun View.invisible() {
    if (visibility != View.INVISIBLE) {
        visibility = View.INVISIBLE
    }
}

@Suppress("unused")
fun View.gone() {
    if (visibility != View.GONE) {
        visibility = View.GONE
    }
}

inline fun View.ifVisible(action: () -> Unit) {
    if (isVisible) action()
}

inline fun View.ifInvisible(action: () -> Unit) {
    if (isInvisible) action()
}

inline fun View.ifGone(action: () -> Unit) {
    if (isGone) action()
}

/**
 *excuseButton.apply {
setOnClickListener {
isExcused = true
updateGrade(null)
}
accessibleTouchTarget()
}
 */
@Suppress("unused")
fun View.accessibleTouchTarget() {
    post {
        val delegateArea = Rect()
        getHitRect(delegateArea)

        // 48 dp is the minimum requirement. We need to convert this to pixels.
        val accessibilityMin = context.dpToPx(48)

        // Calculate size vertically, and adjust touch area if it's smaller then the minimum.
        val height = delegateArea.bottom - delegateArea.top
        if (accessibilityMin > height) {
            // Add +1 px just in case min - height is odd and will be rounded down
            val addition = ((accessibilityMin - height) / 2).toInt() + 1
            delegateArea.top -= addition
            delegateArea.bottom += addition
        }

        // Calculate size horizontally, and adjust touch area if it's smaller then the minimum.
        val width = delegateArea.right - delegateArea.left
        if (accessibilityMin > width) {
            // Add +1 px just in case min - width is odd and will be rounded down
            val addition = ((accessibilityMin - width) / 2).toInt() + 1
            delegateArea.left -= addition
            delegateArea.right += addition
        }

        val parentView = parent as? View
        parentView?.touchDelegate = TouchDelegate(delegateArea, this)
    }
}

// Extension function to convert dp to px.
fun Context.dpToPx(value: Int) = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP,
    value.toFloat(), resources.displayMetrics
)
