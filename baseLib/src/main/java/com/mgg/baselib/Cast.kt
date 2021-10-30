package com.mgg.baselib

@Suppress("unused")
inline fun <reified T> convert(value: String): T {
    when (T::class) {
        Int::class -> {
            return value.toInt() as T
        }
        Long::class -> {
            return value.toLong() as T
        }
        Boolean::class -> {
            return value.toBoolean() as T
        }
        Double::class -> {
            return value.toDouble() as T
        }
        Float::class -> {
            return value.toFloat() as T
        }
        String::class -> {
            return value as T
        }
        Short::class -> {
            return value.toShort() as T
        }
        Character::class -> {
            return value[0] as T
        }
        else -> {
            return value as T
        }
    }
}

@Suppress("unused")
inline fun <reified T> reifiedIs(x: Any): Boolean = x is T

@Suppress("unused")
inline fun <reified T> reifiedIsNot(x: Any): Boolean = x !is T
