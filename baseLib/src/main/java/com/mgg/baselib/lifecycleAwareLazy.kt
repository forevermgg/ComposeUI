@file:Suppress("unused")

package com.mgg.baselib

import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.*
import java.io.Serializable

// https://proandroiddev.com/lifecycle-aware-lazy-property-in-kotlin-for-android-development-94e5615d087f
/*fun <T> LifecycleOwner.lifecycleAwareLazy(initializer: () -> T): Lazy<T> = LifecycleAwareLazy(this, initializer)

class LifecycleAwareLazy<out T>(
    private val owner: LifecycleOwner,
    initializer: () -> T
) : Lazy<T>, Serializable, LifecycleObserver {

    private var initializer: (() -> T)? = initializer

    private var _value: Any? = UninitializedValue

    @Suppress("UNCHECKED_CAST")
    override val value: T
        @MainThread
        get() {
            if (_value === UninitializedValue) {
                _value = initializer!!()
                attachToLifecycle()
            }

            return _value as T
        }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun resetValue() {
        _value = UninitializedValue
        detachFromLifecycle()
    }

    private fun attachToLifecycle() {
        if (getLifecycleOwner().lifecycle.currentState == Lifecycle.State.DESTROYED) {
            throw IllegalStateException("Initialization failed because lifecycle has been destroyed!")
        }
        getLifecycleOwner().lifecycle.addObserver(this)
    }

    private fun detachFromLifecycle() {
        getLifecycleOwner().lifecycle.removeObserver(this)
    }

    private fun getLifecycleOwner() = when (owner) {
        is Fragment -> owner.viewLifecycleOwner
        else -> owner
    }

    override fun isInitialized(): Boolean = _value !== UninitializedValue

    override fun toString(): String = if (isInitialized()) value.toString() else "Lazy value not initialized yet."

    companion object {
        private val UninitializedValue = Any()
    }
}*/

inline fun <reified VM : ViewModel> LifecycleOwner.getViewModel(factory: ViewModelProvider.Factory): VM {
    return when (this) {
        is Fragment -> ViewModelProvider(this, factory).get(VM::class.java)
        is FragmentActivity -> ViewModelProvider(this, factory).get(VM::class.java)
        else -> throw IllegalAccessError("Invalid LifecycleOwner")
    }
}

inline fun <reified VM : ViewModel> Fragment.getSharedViewModel(factory: ViewModelProvider.Factory): VM {
    return ViewModelProvider(requireActivity(), factory).get(VM::class.java)
}

private object UninitializedValue

/**
 * This was copied from SynchronizedLazyImpl but modified to automatically initialize in ON_CREATE.
 */
@SuppressWarnings("Detekt.ClassNaming")
class lifecycleAwareLazy<out T>(
    private val owner: LifecycleOwner,
    isMainThread: () -> Boolean = { Looper.myLooper() == Looper.getMainLooper() },
    initializer: () -> T
) :
    Lazy<T>,
    Serializable {
    private var initializer: (() -> T)? = initializer

    @Volatile
    @SuppressWarnings("Detekt.VariableNaming")
    private var _value: Any? = UninitializedValue

    // final field is required to enable safe publication of constructed instance
    private val lock = this

    init {
        if (isMainThread()) {
            initializeWhenCreated(owner)
        } else {
            Handler(Looper.getMainLooper()).post {
                initializeWhenCreated(owner)
            }
        }
    }

    private fun initializeWhenCreated(owner: LifecycleOwner) {
        val lifecycleState = owner.lifecycle.currentState
        when {
            lifecycleState == Lifecycle.State.DESTROYED || isInitialized() -> return
            lifecycleState == Lifecycle.State.INITIALIZED -> {
                owner.lifecycle.addObserver(object : LifecycleObserver {
                    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
                    fun onCreate(owner: LifecycleOwner) {
                        if (!isInitialized()) value
                        owner.lifecycle.removeObserver(this)
                    }
                })
            }
            else -> {
                if (!isInitialized()) value
            }
        }
    }

    @Suppress("LocalVariableName")
    override val value: T
        get() {
            @SuppressWarnings("Detekt.VariableNaming")
            val _v1 = _value
            if (_v1 !== UninitializedValue) {
                @Suppress("UNCHECKED_CAST")
                return _v1 as T
            }

            return synchronized(lock) {
                @SuppressWarnings("Detekt.VariableNaming")
                val _v2 = _value
                if (_v2 !== UninitializedValue) {
                    @Suppress("UNCHECKED_CAST") (_v2 as T)
                } else {
                    val typedValue = initializer!!()
                    _value = typedValue
                    initializer = null
                    typedValue
                }
            }
        }

    override fun isInitialized(): Boolean = _value !== UninitializedValue

    override fun toString(): String = if (isInitialized()) value.toString() else "Lazy value not initialized yet."
}
