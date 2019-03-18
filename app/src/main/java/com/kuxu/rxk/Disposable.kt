package com.kuxu.rxk

class Disposable<T>(
    val observable: Observable<T>,
    val code: Int = -1
) {
    fun dispose() {
        observable.dispose(this)
    }
}