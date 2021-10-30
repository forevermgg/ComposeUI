package com.mgg.baselib

import android.app.Activity
import android.app.Dialog
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment

/**
 * [Binder] is a delegation class for manual view binding, useful for cases not covered by Kotlin
 * Android Extensions. Generally, bound view properties will not directly instantiate this class.
 * Instead, instantiation of a Binder instance should be handled by an extension function of the
 * class in which views are to be bound, e.g. Dialog.[bind].
 *
 * Example of how to use [Binder] in classes that have a bind() extension function:
 * ```
 * val myImageView by bind<ImageView>(R.id.my_image_view)
 * ```
 *
 * If your layout has multiple views with the same id but different parents:
 * ```
 * val myImageView1 by bind<ImageView>(R.id.my_image_view).withParent(R.id.parent_one)
 * val myImageView2 by bind<ImageView>(R.id.my_image_view).withParent(R.id.parent_two)
 * ```
 * Or, if you already have a reference to the parent views:
 * ```
 * val myImageView1 by bind<ImageView>(R.id.my_image_view).withParent { parentLayout1 }
 * val myImageView2 by bind<ImageView>(R.id.my_image_view).withParent { parentLayout2 }
 * ```
 *
 * For examples on how to create a bind() extension function for a new class, refer to Dialog.bind(),
 * ViewGroup.bind(), Activity.bind(), or Fragment.bind().
 *
 */
@Suppress("UNCHECKED_CAST")
class Binder<in T, out V : View>(@IdRes private val viewId: Int, private val finder: (T, Int) -> View?) : kotlin.properties.ReadOnlyProperty<T, V> {

    private var cachedView: V? = null
    private var useParent = false
    private var parentId: Int? = null
    private var parentProvider: (() -> View)? = null

    override fun getValue(thisRef: T, property: kotlin.reflect.KProperty<*>): V {
        if (cachedView == null) {
            val v: View
            if (useParent) {
                v = when {
                    parentProvider != null -> {
                        val parentView = parentProvider!!.invoke()
                        parentView.findViewById(viewId)
                            ?: throw RuntimeException("Unable to bind ${property.name}; view not found in provided parent ${parentView.javaClass.simpleName}")
                    }
                    parentId != null -> {
                        val parentView = finder(thisRef, parentId!!)
                            ?: throw RuntimeException("Unable to bind ${property.name}; could not find specified parent with id ${(parentId!!)}")
                        parentView.findViewById(viewId)
                            ?: throw RuntimeException("Unable to bind ${property.name}; view not found in specified parent with id ${(parentId!!)}")
                    }
                    else -> throw RuntimeException("Unable to bind ${property.name}; please provide parent view or specify parent view id")
                }
            } else {
                v = finder(thisRef, viewId) ?: throw RuntimeException("Unable to bind ${property.name}; findViewById returned null.")
            }
            cachedView = v as V
        }
        return cachedView!!
    }

    fun withParent(@IdRes parentId: Int): Binder<T, V> {
        useParent = true
        this.parentId = parentId
        return this
    }

    fun withParent(parentProvider: () -> View): Binder<T, V> {
        useParent = true
        this.parentProvider = parentProvider
        return this
    }

}

/** Provides a view-binding delegate inside classes extending [Dialog]. See [Binder] for more information. */
inline fun <reified V : View> Dialog.bind(@IdRes id: Int): Binder<Dialog, V> = Binder(id) { dialog, viewId -> dialog.findViewById<V>(viewId) }

/** Provides a view-binding delegate inside classes extending [ViewGroup]. See [Binder] for more information. */
inline fun <reified V : View> ViewGroup.bind(@IdRes id: Int): Binder<ViewGroup, V> =
    Binder(id) { viewGroup, viewId -> viewGroup.findViewById<V>(viewId) }

/** Provides a view-binding delegate inside classes extending [Activity]. See [Binder] for more information. */
inline fun <reified V : View> Activity.bind(@IdRes id: Int): Binder<Activity, V> = Binder(id) { activity, viewId -> activity.findViewById<V>(viewId) }

/** Provides a view-binding delegate inside classes extending [Fragment]. See [Binder] for more information. */
fun <V : View> Fragment.bind(@IdRes id: Int): Binder<Fragment, V> = Binder(id) { it, viewId -> it.view?.findViewById(viewId) }