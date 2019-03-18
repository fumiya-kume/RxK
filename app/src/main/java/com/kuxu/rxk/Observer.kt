package com.kuxu.rxk

internal data class Observer<T>(
    val onNext: (T) -> Unit,
    val onError: (e: Throwable) -> Unit,
    val onCompleted: (Unit) -> Unit
)